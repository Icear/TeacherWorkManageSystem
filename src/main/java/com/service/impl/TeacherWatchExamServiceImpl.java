package com.service.impl;

import com.entity.TeacherWatchExamEntity;
import com.service.TeacherWatchExamService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TeacherWatchExamServiceImpl implements TeacherWatchExamService {

    @Override
    public @NotNull TeacherWatchExamEntity addTeacherWatchExam(@NotNull TeacherWatchExamEntity teacherWatchExamEntity) {
        return null;
    }

    @Override
    public void deleteTeacherWatchExam(@NotNull TeacherWatchExamEntity teacherWatchExamEntity) {

    }

    @Override
    public @NotNull Optional<TeacherWatchExamEntity> updateTeacherWatchExam(@NotNull TeacherWatchExamEntity teacherWatchExamEntity) {
        return Optional.empty();
    }

    @Override
    public @NotNull List<TeacherWatchExamEntity> findTeacherWatchExams() {
        return null;
    }
}
