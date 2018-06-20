package com.service.impl;

import com.dao.AdministratorEntityDao;
import com.dao.TeacherEntityDao;
import com.entity.TeacherEntity;
import com.service.TeacherService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {
    private final TeacherEntityDao teacherEntityDao;
    private final AdministratorEntityDao administratorEntityDao;

    @Autowired
    public TeacherServiceImpl(TeacherEntityDao teacherEntityDao, AdministratorEntityDao administratorEntityDao) {
        this.teacherEntityDao = teacherEntityDao;
        this.administratorEntityDao = administratorEntityDao;
    }

    @Override
    public @NotNull Optional<TeacherEntity> findTeacher(int id) {
        return Optional.ofNullable(teacherEntityDao.find(id));
    }

    @Override
    public @NotNull Optional<TeacherEntity> findTeacher(@NotNull String account) {
        return Optional.ofNullable(teacherEntityDao.findByAccount(account));
    }

    @Override
    public @NotNull List<TeacherEntity> findTeacherByName(@NotNull String name) {
        return teacherEntityDao.findByName(name);
    }

    @Override
    public @NotNull List<TeacherEntity> findTeachers() {
        return teacherEntityDao.list();
    }

    @Override
    public @NotNull TeacherEntity addTeacher(@NotNull TeacherEntity teacher) {
        return teacherEntityDao.add(teacher);
    }

    @Override
    public void deleteTeacher(@NotNull TeacherEntity teacher) {
        teacherEntityDao.delete(teacher);
    }

    @Override
    public @NotNull Optional<TeacherEntity> updateTeacher(@NotNull TeacherEntity teacherEntity) {
        return Optional.ofNullable(teacherEntityDao.edit(teacherEntity));
    }

    @Override
    public boolean isAdministrator(@NotNull TeacherEntity teacher) {
        return administratorEntityDao.findByTeacherId(teacher.getId()) != null;
    }
}
