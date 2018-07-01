package com.service.impl;

import com.dao.ClassroomEntityDao;
import com.entity.ClassroomEntity;
import com.service.ClassroomService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassroomServiceImpl implements ClassroomService {
    private final ClassroomEntityDao classroomEntityDao;

    @Autowired
    public ClassroomServiceImpl(ClassroomEntityDao classroomEntityDao) {
        this.classroomEntityDao = classroomEntityDao;
    }

    @Override
    public @NotNull ClassroomEntity addClassroom(@NotNull ClassroomEntity classroom) {
        return classroomEntityDao.add(classroom);
    }

    @Override
    public void deleteClassroom(ClassroomEntity classroom) {
        classroomEntityDao.delete(classroom);
    }

    @Override
    public @NotNull Optional<ClassroomEntity> updateClassroom(@NotNull ClassroomEntity classroom) {
        return Optional.ofNullable(classroomEntityDao.edit(classroom));
    }

    @Override
    public @NotNull List<ClassroomEntity> findClassrooms() {
        return classroomEntityDao.list();
    }

    @Override
    public @NotNull Optional<ClassroomEntity> findClassroomByClassroomId(int classroomId) {
        return Optional.ofNullable(classroomEntityDao.find(classroomId));
    }

    @Override
    public @NotNull List<ClassroomEntity> findClassroomsByExamId(int examId) {
        return classroomEntityDao.findByExamEntity(examId);
    }
}
