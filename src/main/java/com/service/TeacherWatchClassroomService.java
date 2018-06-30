package com.service;

import com.entity.TeacherWatchClassroomEntity;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public interface TeacherWatchClassroomService {
    /**
     * 添加监考信息
     *
     * @param teacherWatchClassroomEntity 教师监考考试实体
     * @return 成功返回新的实体类
     */
    @NotNull TeacherWatchClassroomEntity addTeacherWatchClassroom(@NotNull TeacherWatchClassroomEntity teacherWatchClassroomEntity);

    /**
     * 删除指定监考信息
     *
     * @param teacherWatchClassroomEntity 教师监考考试实体
     */
    void deleteTeacherWatchClassroom(@NotNull TeacherWatchClassroomEntity teacherWatchClassroomEntity);

    // TODO: 2018/6/14 如何修改监考信息，两个都是主键，应该如何修改？

    /**
     * 更新监考信息
     *
     * @param teacherWatchClassroomEntity 教师监考信息实体
     * @return 成功返回修改后的新的实体
     */
    @NotNull Optional<TeacherWatchClassroomEntity> updateTeacherWatchClassroom(@NotNull TeacherWatchClassroomEntity teacherWatchClassroomEntity);

    /**
     * 查询所有监考信息
     *
     * @return 监考信息实体集合
     */
    @NotNull List<TeacherWatchClassroomEntity> findTeacherWatchClassrooms();

    Optional<TeacherWatchClassroomEntity> findById(@NotNull Integer id);

    // @NotNull List<TeacherEntity> findTeachersInOneExam(int examId);
}
