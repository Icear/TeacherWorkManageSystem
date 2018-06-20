package com.service;

import com.entity.ExamEntity;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public interface ExamService {

    /**
     * 添加考试信息
     *
     * @param exam 考试对象
     * @return 成功返回新的实体对象，失败返回null
     */
    @NotNull Optional<ExamEntity> addExam(@NotNull ExamEntity exam);

    /**
     * 删除考试
     *
     * @param exam 有效的含有id的考试类
     * @return 考试删除成功与否
     */
    boolean deleteExam(@NotNull ExamEntity exam);

    /**
     * 根据考试id查询考试信息
     *
     * @param examId 考试id
     * @return 考试对象
     */
    @NotNull Optional<ExamEntity> findExamByExamId(int examId);

    /**
     * 根据课程id来查询考试考试信息
     *
     * @param courseId 课程id
     * @return 考试对象集合
     */
    @NotNull List<ExamEntity> findExamsByCourseId(int courseId);

    /**
     * 根据考试名称查询考试信息
     *
     * @param examName 考试名称
     * @return 考试对象集合
     */
    @NotNull List<ExamEntity> findExamsByExamName(@NotNull String examName);

    /**
     * 根据管理员id查询管理员所创建的考试信息
     *
     * @param adminId 管理员id
     * @return 考试对象集合
     */
    @NotNull List<ExamEntity> findExamsByAdminId(int adminId);

    /**
     * 查询所有考试
     *
     * @return 考试对象集合
     */
    @NotNull List<ExamEntity> findAllExams();

    /**
     * 更新考试信息
     *
     * @param exam 新的考试对象，exam中应带有旧的examId
     * @return 成功返回新的exam实体对象
     */
    @NotNull Optional<ExamEntity> updateExam(ExamEntity exam);

}
