package com.service;

import com.entity.ClassroomEntity;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public interface ClassroomService {

    /**
     * 添加教室
     *
     * @param classroom 教室实体对象
     * @return 成功则返回新的教室实体，失败返回null
     */
    @NotNull Optional<ClassroomEntity> addClassroom(@NotNull ClassroomEntity classroom);

    /**
     * 删除教室
     *
     * @param classroom 有效的含有教室id的教室类
     * @return 删除成功与否
     */
    boolean deleteClassroom(ClassroomEntity classroom);

    /**
     * 更新教室
     *
     * @param classroom 教室实体，应包含被修改的教室id
     * @return 更新成功返回新的教室实体，失败返回null
     */
    @NotNull Optional<ClassroomEntity> updateClassroom(@NotNull ClassroomEntity classroom);

    /**
     * 查找所有的教室
     *
     * @return 教室实体对象集合
     */
    @NotNull List<ClassroomEntity> findClassrooms();

    /**
     * 根据教室id查询教室信息
     *
     * @param classroomId 教室id
     * @return 教室对象
     */
    @NotNull Optional<ClassroomEntity> findClassroomByClassroomId(int classroomId);

    /**
     * 根据考试id查询占用的教室信息
     *
     * @param examId 考试id
     * @return 教室对象集合
     */
    @NotNull List<ClassroomEntity> findClassroomsByExamId(int examId);
}
