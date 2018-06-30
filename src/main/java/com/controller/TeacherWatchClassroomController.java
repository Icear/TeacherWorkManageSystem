package com.controller;

import com.constattribute.RequestPathName;
import com.entity.TeacherWatchClassroomEntity;
import com.service.TeacherWatchClassroomService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


@Controller
public class TeacherWatchClassroomController {
    private Logger logger = LogManager.getLogger(TeacherWatchClassroomController.class);
    private TeacherWatchClassroomService teacherWatchClassroomService;

    @Autowired
    public TeacherWatchClassroomController(TeacherWatchClassroomService teacherWatchClassroomService) {
        this.teacherWatchClassroomService = teacherWatchClassroomService;
    }

    @GetMapping(RequestPathName.TEACHER_WATCH_CLASSROOMS)
    public List<TeacherWatchClassroomEntity> getAllTeacherWatchClassroom() {
        //TODO 加入缓存控制
        return teacherWatchClassroomService.findTeacherWatchClassrooms();
    }


    @GetMapping(RequestPathName.TEACHER_WATCH_CLASSROOMS + "/{id}")
    @ResponseBody
    @Validated
    public TeacherWatchClassroomEntity getTeacherWatchClassroom(@PathVariable @NotNull Integer id) {
        logger.info("get TeacherWatchClassroom, id: " + id);
        return teacherWatchClassroomService.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @PostMapping(RequestPathName.TEACHER_WATCH_CLASSROOMS)
    @Validated
    public ResponseEntity<TeacherWatchClassroomEntity> createTeacherWatchClassroom(@NotNull TeacherWatchClassroomEntity teacherWatchClassroomEntity) throws URISyntaxException {
        TeacherWatchClassroomEntity createdTeacherWatchClassroomEntity = teacherWatchClassroomService.addTeacherWatchClassroom(teacherWatchClassroomEntity);
        return ResponseEntity.created(new URI(RequestPathName.TEACHER_WATCH_CLASSROOMS + "/" + createdTeacherWatchClassroomEntity.getId())).build();
    }

    @PatchMapping(RequestPathName.TEACHER_WATCH_CLASSROOMS + "/{id}")
    @Validated
    public ResponseEntity<TeacherWatchClassroomEntity> updateTeacherWatchClassroom(@NotNull TeacherWatchClassroomEntity teacherWatchClassroomEntity, @NotNull @PathVariable Integer id) {
        teacherWatchClassroomEntity.setId(id);
        if (teacherWatchClassroomService.updateTeacherWatchClassroom(teacherWatchClassroomEntity).isPresent()) {
            return ResponseEntity.ok().build();
        } else {
            //更新失败，返回服务器无法处理目标请求
            throw new EntityNotFoundException("Entity Update Failed");
//            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }

    @DeleteMapping(RequestPathName.TEACHER_WATCH_CLASSROOMS + "/{id}")
    @Validated
    public ResponseEntity<TeacherWatchClassroomEntity> deleteTeacherWatchClassroom(@NotNull @PathVariable Integer id) {
        TeacherWatchClassroomEntity teacherWatchClassroomEntity = teacherWatchClassroomService.findById(id).orElseThrow(EntityNotFoundException::new);
        teacherWatchClassroomService.deleteTeacherWatchClassroom(teacherWatchClassroomEntity);
        return ResponseEntity.ok().build();
    }

}
