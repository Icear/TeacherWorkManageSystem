package com.service;

import com.entity.CourseEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

public interface CourseService {
    /**
     * 添加课程
     *
     * @param course 课程实体
     * @return 是否添加成功
     */
    boolean addCourse(@NotNull CourseEntity course);

    /**
     * 根据课程id删除课程
     *
     * @param courseId 课程id
     * @return 是否删除成功
     */
    boolean deleteCourse(int courseId);

    /**
     * 更新课程信息
     *
     * @param course 课程实体，课程中应带有被修改的课程id
     * @return 是否更新成功
     */
    boolean updateCourse(@NotNull CourseEntity course);

    /**
     * 查询所有课程信息
     *
     * @return 课程对象集合
     */
    @NotNull Set<CourseEntity> findCourses();

    /**
     * 根据课程id查询课程信息
     *
     * @param courseId 课程id
     * @return 课程对象
     */
    @Nullable CourseEntity findCourseByCourseId(int courseId);

    /**
     * 根据课程名称查询课程信息
     *
     * @param courseName 课程名称
     * @return 课程对象集合
     */
    @NotNull Set<CourseEntity> findCoursesByCourseName(@NotNull String courseName);
}
