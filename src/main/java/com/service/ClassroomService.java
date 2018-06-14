package com.service;

import com.entity.ClassroomEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

public interface ClassroomService {

    /**
     * 添加教室
     *
     * @param classroom 教室实体对象
     * @return 是否添加成功
     */
    boolean addClassroom(@NotNull ClassroomEntity classroom);

    /**
     * 删除教室
     *
     * @param classroomId 教室id
     * @return 删除成功与否
     */
    boolean deleteClassroom(int classroomId);

    /**
     * 更新教室
     *
     * @param classroom 教室实体，应包含被修改的教室id
     * @return 删除成功与否
     */
    boolean updateClassroom(@NotNull ClassroomEntity classroom);

    /**
     * 查找所有的教室
     *
     * @return 教室实体对象集合
     */
    @NotNull Set<ClassroomEntity> findClassrooms();

    /**
     * 根据教室id查询教室信息
     *
     * @param classroomId 教室id
     * @return 教室对象
     */
    @Nullable ClassroomEntity findClassroomByClassroomId(int classroomId);

    /**
     * 根据考试id查询占用的教室信息
     *
     * @param examId 考试id
     * @return 教室对象集合
     */
    @NotNull Set<ClassroomEntity> findClassroomsByExamId(int examId);
}
