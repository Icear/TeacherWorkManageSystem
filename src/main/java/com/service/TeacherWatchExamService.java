package com.service;

import com.entity.TeacherWatchExamEntity;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public interface TeacherWatchExamService {
    /**
     * 添加监考信息
     *
     * @param teacherWatchExamEntity 教师监考考试实体
     * @return 成功返回新的实体类
     */
    @NotNull TeacherWatchExamEntity addTeacherWatchExam(@NotNull TeacherWatchExamEntity teacherWatchExamEntity);

    /**
     * 删除指定监考信息
     *
     * @param teacherWatchExamEntity 教师监考考试实体
     */
    void deleteTeacherWatchExam(@NotNull TeacherWatchExamEntity teacherWatchExamEntity);

    // TODO: 2018/6/14 如何修改监考信息，两个都是主键，应该如何修改？

    /**
     * 更新监考信息
     *
     * @param teacherWatchExamEntity 教师监考信息实体
     * @return 成功返回修改后的新的实体
     */
    @NotNull Optional<TeacherWatchExamEntity> updateTeacherWatchExam(@NotNull TeacherWatchExamEntity teacherWatchExamEntity);

    /**
     * 查询所有监考信息
     *
     * @return 监考信息实体集合
     */
    @NotNull List<TeacherWatchExamEntity> findTeacherWatchExams();


    // @NotNull List<TeacherEntity> findTeachersInOneExam(int examId);
}
