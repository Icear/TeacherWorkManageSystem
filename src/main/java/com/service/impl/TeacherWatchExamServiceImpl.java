package com.service.impl;

import com.dao.TeacherWatchExamEntityDao;
import com.entity.TeacherWatchExamEntity;
import com.service.TeacherWatchExamService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TeacherWatchExamServiceImpl implements TeacherWatchExamService {
    private final TeacherWatchExamEntityDao teacherWatchExamEntityDao;

    @Autowired
    public TeacherWatchExamServiceImpl(TeacherWatchExamEntityDao teacherWatchExamEntityDao) {
        this.teacherWatchExamEntityDao = teacherWatchExamEntityDao;
    }

    @Override
    public @NotNull TeacherWatchExamEntity addTeacherWatchExam(@NotNull TeacherWatchExamEntity teacherWatchExamEntity) {
        return teacherWatchExamEntityDao.add(teacherWatchExamEntity);
    }

    @Override
    public void deleteTeacherWatchExam(@NotNull TeacherWatchExamEntity teacherWatchExamEntity) {
        teacherWatchExamEntityDao.delete(teacherWatchExamEntity);
    }

    @Override
    public @NotNull Optional<TeacherWatchExamEntity> updateTeacherWatchExam(@NotNull TeacherWatchExamEntity teacherWatchExamEntity) {
        return Optional.ofNullable(teacherWatchExamEntityDao.edit(teacherWatchExamEntity));
    }

    @Override
    public @NotNull List<TeacherWatchExamEntity> findTeacherWatchExams() {
        return teacherWatchExamEntityDao.list();
    }
}
