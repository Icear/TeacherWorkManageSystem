package com.service.impl;

import com.dao.TeacherWatchClassroomEntityDao;
import com.entity.TeacherWatchClassroomEntity;
import com.service.TeacherWatchClassroomService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TeacherWatchClassroomServiceImpl implements TeacherWatchClassroomService {
    private final TeacherWatchClassroomEntityDao teacherWatchClassroomEntityDao;

    @Autowired
    public TeacherWatchClassroomServiceImpl(TeacherWatchClassroomEntityDao teacherWatchClassroomEntityDao) {
        this.teacherWatchClassroomEntityDao = teacherWatchClassroomEntityDao;
    }

    @Override
    public @NotNull TeacherWatchClassroomEntity addTeacherWatchClassroom(@NotNull TeacherWatchClassroomEntity teacherWatchClassroomEntity) {
        return teacherWatchClassroomEntityDao.add(teacherWatchClassroomEntity);
    }

    @Override
    public void deleteTeacherWatchClassroom(@NotNull TeacherWatchClassroomEntity teacherWatchClassroomEntity) {
        teacherWatchClassroomEntityDao.delete(teacherWatchClassroomEntity);
    }

    @Override
    public @NotNull Optional<TeacherWatchClassroomEntity> updateTeacherWatchClassroom(@NotNull TeacherWatchClassroomEntity teacherWatchClassroomEntity) {
        return Optional.ofNullable(teacherWatchClassroomEntityDao.edit(teacherWatchClassroomEntity));
    }

    @Override
    public @NotNull List<TeacherWatchClassroomEntity> findTeacherWatchClassrooms() {
        return teacherWatchClassroomEntityDao.list();
    }

    @Override
    public Optional<TeacherWatchClassroomEntity> findById(@NotNull Integer id) {
        return Optional.ofNullable(teacherWatchClassroomEntityDao.find(id));
    }
}
