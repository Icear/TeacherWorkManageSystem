package com.service.impl;

import com.dao.CourseEntityDao;
import com.entity.CourseEntity;
import com.service.CourseService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {
    private final CourseEntityDao courseEntityDao;

    @Autowired
    public CourseServiceImpl(CourseEntityDao courseEntityDao) {
        this.courseEntityDao = courseEntityDao;
    }

    @Override
    public @NotNull CourseEntity addCourse(@NotNull CourseEntity course) {
        return courseEntityDao.add(course);
    }

    @Override
    public void deleteCourse(@NotNull CourseEntity course) {
        courseEntityDao.delete(course);
    }

    @Override
    public @NotNull Optional<CourseEntity> updateCourse(@NotNull CourseEntity course) {
        return Optional.ofNullable(courseEntityDao.edit(course));
    }

    @Override
    public @NotNull List<CourseEntity> findCourses() {
        return courseEntityDao.list();
    }

    @Override
    public @NotNull Optional<CourseEntity> findCourseByCourseId(int courseId) {
        return Optional.ofNullable(courseEntityDao.find(courseId));
    }

    @Override
    public @NotNull Optional<CourseEntity> findCoursesByCourseName(@NotNull String courseName) {
        return Optional.ofNullable(courseEntityDao.findByCourseName(courseName));
    }
}
