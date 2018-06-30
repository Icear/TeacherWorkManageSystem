package com.controller;

import com.annotation.Authority;
import com.constattribute.RequestPathName;
import com.entity.ClassroomEntity;
import com.identity.Identity;
import com.service.ClassroomService;
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
public class ClassroomController {
    private Logger logger = LogManager.getLogger(ClassroomController.class);
    private ClassroomService classroomService;

    @Autowired
    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @Authority(role = Identity.User)
    @Authority(role = Identity.Administrator)
    @GetMapping(RequestPathName.CLASSROOMS)
    public List<ClassroomEntity> getAllClassroom() {
        //TODO 加入缓存控制
        logger.info("get all classroom");
        return classroomService.findClassrooms();
    }

    @Authority(role = Identity.User)
    @Authority(role = Identity.Administrator)
    @GetMapping(RequestPathName.CLASSROOMS + "/{id}")
    @ResponseBody
    @Validated
    public ClassroomEntity getClassroom(@PathVariable @NotNull Integer id) {
        logger.info("get Classroom, id: " + id);
        return classroomService.findClassroomByClassroomId(id).orElseThrow(EntityNotFoundException::new);
    }

    @Authority(role = Identity.Administrator)
    @PostMapping(RequestPathName.CLASSROOMS)
    @Validated
    public ResponseEntity<ClassroomEntity> createClassroom(@NotNull ClassroomEntity classroomEntity) throws URISyntaxException {
        logger.debug(classroomEntity);
        ClassroomEntity createdClassroomEntity = classroomService.addClassroom(classroomEntity);
        logger.info("classroom " + createdClassroomEntity.getId() + " created");
        logger.debug(createdClassroomEntity);
        return ResponseEntity.created(new URI(RequestPathName.CLASSROOMS + "/" + createdClassroomEntity.getId())).build();
    }

    @Authority(role = Identity.Administrator)
    @PatchMapping(RequestPathName.CLASSROOMS + "/{id}")
    @Validated
    public ResponseEntity<ClassroomEntity> updateClassroom(@NotNull ClassroomEntity classroomEntity, @NotNull @PathVariable Integer id) {
        logger.debug(classroomEntity);
        classroomEntity.setId(id);
        Optional<ClassroomEntity> updatedClassroomEntity = classroomService.updateClassroom(classroomEntity);
        if (updatedClassroomEntity.isPresent()) {
            logger.info("classroom " + id + " updated");
            logger.debug(updatedClassroomEntity.get());
            return ResponseEntity.ok().build();
        } else {
            //更新失败，返回服务器无法处理目标请求
            throw new EntityNotFoundException("Entity Update Failed");
//            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }

    @Authority(role = Identity.Administrator)
    @DeleteMapping(RequestPathName.CLASSROOMS + "/{id}")
    @Validated
    public ResponseEntity<ClassroomEntity> deleteClassroom(@NotNull @PathVariable Integer id) {
        ClassroomEntity classroomEntity = classroomService.findClassroomByClassroomId(id).orElseThrow(EntityNotFoundException::new);
        classroomService.deleteClassroom(classroomEntity);
        logger.info("classroom " + id + " removed");
        return ResponseEntity.ok().build();
    }

}
