package com.service;

import com.entity.ExamEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

public interface ExamService {

    /**
     * 添加考试信息
     *
     * @param exam 考试对象
     * @return 考试添加成功与否（用try catch语句，如果catch到exception则返回否？
     */
    boolean addExam(@NotNull ExamEntity exam);

    /**
     * 删除考试
     *
     * @param examId 考试id
     * @return 考试删除成功与否
     */
    boolean deleteExam(int examId);

    /**
     * 根据考试id查询考试信息
     *
     * @param examId 考试id
     * @return 考试对象
     */
    @Nullable ExamEntity findExamByExamId(int examId);

    /**
     * 根据课程id来查询考试考试信息
     *
     * @param courseId 课程id
     * @return 考试对象集合
     */
    @NotNull Set<ExamEntity> findExamsByCourseId(int courseId);

    /**
     * 根据考试名称查询考试信息
     *
     * @param examName 考试名称
     * @return 考试对象集合
     */
    @NotNull Set<ExamEntity> findExamsByExamName(@NotNull String examName);

    /**
     * 根据管理员id查询管理员所创建的考试信息
     *
     * @param adminId 管理员id
     * @return 考试对象集合
     */
    @NotNull Set<ExamEntity> findExamsByAdminId(int adminId);

    /**
     * 查询所有考试
     *
     * @return 考试对象集合
     */
    @NotNull Set<ExamEntity> findAllExams();

    /**
     * 更新考试信息
     *
     * @param exam 新的考试对象，exam中应带有旧的examId
     * @return 更新成功与否
     */
    boolean updateExam(ExamEntity exam);

}
