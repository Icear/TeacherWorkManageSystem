package com.controller;

import com.annotation.Authority;
import com.constattribute.RequestPathName;
import com.entity.TeacherEntity;
import com.identity.Identity;
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
import java.util.Optional;


@Controller
public class TeacherController {
    private Logger logger = LogManager.getLogger(TeacherController.class);
    private TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @Authority(role = Identity.User)
    @Authority(role = Identity.Administrator)
    @GetMapping(RequestPathName.TEACHERS)
    public List<TeacherEntity> getAllTeacher() {
        //TODO 加入缓存控制
        logger.info("get all teachers");
        return teacherService.findTeachers();
    }

    @Authority(role = Identity.User)
    @Authority(role = Identity.Administrator)
    @GetMapping(RequestPathName.TEACHERS + "/{id}")
    @ResponseBody
    @Validated
    public TeacherEntity getTeacher(@PathVariable @NotNull Integer id) {
        logger.info("get Teacher, id: " + id);
        return teacherService.findTeacher(id).orElseThrow(EntityNotFoundException::new);
    }

    @Authority(role = Identity.Administrator)
    @PostMapping(RequestPathName.TEACHERS)
    @Validated
    public ResponseEntity<TeacherEntity> createTeacher(@NotNull TeacherEntity teacherEntity) throws URISyntaxException {
        logger.debug(teacherEntity);
        TeacherEntity createdTeacherEntity = teacherService.addTeacher(teacherEntity);
        logger.info("teacher " + createdTeacherEntity.getId() + " created");
        logger.debug(createdTeacherEntity);
        return ResponseEntity.created(new URI(RequestPathName.TEACHERS + "/" + createdTeacherEntity.getId())).build();
    }

    @Authority(role = Identity.User)
    @Authority(role = Identity.Administrator)
    @PatchMapping(RequestPathName.TEACHERS + "/{id}")
    @Validated
    public ResponseEntity<TeacherEntity> updateTeacher(@NotNull TeacherEntity teacherEntity, @NotNull @PathVariable Integer id) {
        logger.debug(teacherEntity);
        teacherEntity.setId(id);
        Optional<TeacherEntity> updatedTeacherEntity = teacherService.updateTeacher(teacherEntity);
        if (updatedTeacherEntity.isPresent()) {
            logger.info("teacher " + id + " updated");
            logger.debug(updatedTeacherEntity.get());
            return ResponseEntity.ok().build();
        } else {
            //更新失败，返回服务器无法处理目标请求
            throw new EntityNotFoundException("Entity Update Failed");
//            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }

    @Authority(role = Identity.Administrator)
    @DeleteMapping(RequestPathName.TEACHERS + "/{id}")
    @Validated
    public ResponseEntity<TeacherEntity> deleteTeacher(@NotNull @PathVariable Integer id) {
        TeacherEntity teacherEntity = teacherService.findTeacher(id).orElseThrow(EntityNotFoundException::new);
        teacherService.deleteTeacher(teacherEntity);
        logger.info("teacher " + id + "removed");
        return ResponseEntity.ok().build();
    }

}
