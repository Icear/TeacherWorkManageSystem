package com.service.impl;

import com.dao.ExamEntityDao;
import com.entity.ExamEntity;
import com.service.ExamService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ExamServiceImpl implements ExamService {
    private final ExamEntityDao examEntityDao;

    @Autowired
    public ExamServiceImpl(ExamEntityDao examEntityDao) {
        this.examEntityDao = examEntityDao;
    }

    @Override
    public @NotNull ExamEntity addExam(@NotNull ExamEntity exam) {
        return examEntityDao.add(exam);
    }

    @Override
    public void deleteExam(@NotNull ExamEntity exam) {
        examEntityDao.delete(exam);
    }

    @Override
    public @NotNull Optional<ExamEntity> findExamByExamId(int examId) {
        return Optional.ofNullable(examEntityDao.find(examId));
    }

    @Override
    public @NotNull List<ExamEntity> findExamsByCourseId(int courseId) {
        return examEntityDao.findByCourseId(courseId);
    }

    @Override
    public @NotNull List<ExamEntity> findExamsByExamName(@NotNull String examName) {
        return examEntityDao.findByName(examName);
    }

    @Override
    public @NotNull List<ExamEntity> findExamsByAdminId(int adminId) {
        return examEntityDao.findByAdministratorId(adminId);
    }

    @Override
    public @NotNull List<ExamEntity> findAllExams() {
        return examEntityDao.list();
    }

    @Override
    public @NotNull Optional<ExamEntity> updateExam(ExamEntity exam) {
        return Optional.ofNullable(examEntityDao.edit(exam));
    }
}
