package com.service;

import com.entity.CourseEntity;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    /**
     * 添加课程
     *
     * @param course 课程实体
     * @return 成功则返回新的此course实体, 失败返回null
     */
    @NotNull CourseEntity addCourse(@NotNull CourseEntity course);

    /**
     * 根据课程id删除课程
     *
     * @param course 有效的课程类
     */
    void deleteCourse(@NotNull CourseEntity course);

    /**
     * 更新课程信息
     *
     * @param course 课程实体，课程中应带有被修改的课程id
     * @return 更新成功则返回新的course实体
     */
    @NotNull Optional<CourseEntity> updateCourse(@NotNull CourseEntity course);

    /**
     * 查询所有课程信息
     *
     * @return 课程对象集合
     */
    @NotNull List<CourseEntity> findCourses();

    /**
     * 根据课程id查询课程信息
     *
     * @param courseId 课程id
     * @return 课程对象
     */
    @NotNull Optional<CourseEntity> findCourseByCourseId(int courseId);

    /**
     * 根据课程名称查询课程信息
     *
     * @param courseName 课程名称
     * @return 课程对象
     */
    @NotNull Optional<CourseEntity> findCoursesByCourseName(@NotNull String courseName);
}
