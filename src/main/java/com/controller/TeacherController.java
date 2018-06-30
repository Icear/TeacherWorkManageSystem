package com.controller;

import com.constattribute.RequestPathName;
import com.entity.TeacherEntity;
import com.service.TeacherService;
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
public class TeacherController {
    private Logger logger = LogManager.getLogger(TeacherController.class);
    private TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping(RequestPathName.TEACHERS)
    public List<TeacherEntity> getAllTeacher() {
        //TODO 加入缓存控制
        return teacherService.findTeachers();
    }


    @GetMapping(RequestPathName.TEACHERS + "/{id}")
    @ResponseBody
    @Validated
    public TeacherEntity getTeacher(@PathVariable @NotNull Integer id) {
        logger.info("get Teacher, id: " + id);
        return teacherService.findTeacher(id).orElseThrow(EntityNotFoundException::new);
    }

    @PostMapping(RequestPathName.TEACHERS)
    @Validated
    public ResponseEntity<TeacherEntity> createTeacher(@NotNull TeacherEntity teacherEntity) throws URISyntaxException {
        TeacherEntity createdTeacherEntity = teacherService.addTeacher(teacherEntity);
        return ResponseEntity.created(new URI(RequestPathName.TEACHERS + "/" + createdTeacherEntity.getId())).build();
    }

    @PatchMapping(RequestPathName.TEACHERS + "/{id}")
    @Validated
    public ResponseEntity<TeacherEntity> updateTeacher(@NotNull TeacherEntity teacherEntity, @NotNull @PathVariable Integer id) {
        teacherEntity.setId(id);
        if (teacherService.updateTeacher(teacherEntity).isPresent()) {
            return ResponseEntity.ok().build();
        } else {
            //更新失败，返回服务器无法处理目标请求
            throw new EntityNotFoundException("Entity Update Failed");
//            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }

    @DeleteMapping(RequestPathName.TEACHERS + "/{id}")
    @Validated
    public ResponseEntity<TeacherEntity> deleteTeacher(@NotNull @PathVariable Integer id) {
        TeacherEntity teacherEntity = teacherService.findTeacher(id).orElseThrow(EntityNotFoundException::new);
        teacherService.deleteTeacher(teacherEntity);
        return ResponseEntity.ok().build();
    }

}
