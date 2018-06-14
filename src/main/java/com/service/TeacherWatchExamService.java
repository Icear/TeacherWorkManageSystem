package com.service;

import com.entity.TeacherWatchExamEntity;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public interface TeacherWatchExamService {
    /**
     * 添加监考信息
     *
     * @param teacherWatchExamEntity 教师监考考试实体
     * @return 是否添加成功
     */
    boolean addTeacherWatchExam(@NotNull TeacherWatchExamEntity teacherWatchExamEntity);

    /**
     * 删除指定监考信息
     *
     * @param teacherWatchExamEntity 教师监考考试实体
     * @return 是否删除成功
     */
    boolean deleteTeacherWatchExam(@NotNull TeacherWatchExamEntity teacherWatchExamEntity);

    // TODO: 2018/6/14 如何修改监考信息，两个都是主键，应该如何修改？

    /**
     * 更新监考信息
     *
     * @param teacherWatchExamEntity 教师监考信息实体
     * @return 是否修改成功
     */
    boolean updateTeacherWatchExam(@NotNull TeacherWatchExamEntity teacherWatchExamEntity);

    /**
     * 查询所有监考信息
     *
     * @return 监考信息实体集合
     */
    @NotNull Set<TeacherWatchExamEntity> findTeacherWatchExams();
}
