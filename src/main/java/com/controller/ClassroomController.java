package com.controller;

import com.constattribute.RequestPathName;
import com.entity.ClassroomEntity;
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


@Controller
public class ClassroomController {
    private Logger logger = LogManager.getLogger(ClassroomController.class);
    private ClassroomService classroomService;

    @Autowired
    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @GetMapping(RequestPathName.CLASSROOMS)
    public List<ClassroomEntity> getAllClassroom() {
        //TODO 加入缓存控制
        return classroomService.findClassrooms();
    }


    @GetMapping(RequestPathName.CLASSROOMS + "/{id}")
    @ResponseBody
    @Validated
    public ClassroomEntity getClassroom(@PathVariable @NotNull Integer id) {
        logger.info("get Classroom, id: " + id);
        return classroomService.findClassroomByClassroomId(id).orElseThrow(EntityNotFoundException::new);
    }

    @PostMapping(RequestPathName.CLASSROOMS)
    @Validated
    public ResponseEntity<ClassroomEntity> createClassroom(@NotNull ClassroomEntity classroomEntity) throws URISyntaxException {
        ClassroomEntity createdClassroomEntity = classroomService.addClassroom(classroomEntity);
        return ResponseEntity.created(new URI(RequestPathName.CLASSROOMS + "/" + createdClassroomEntity.getId())).build();
    }

    @PatchMapping(RequestPathName.CLASSROOMS + "/{id}")
    @Validated
    public ResponseEntity<ClassroomEntity> updateClassroom(@NotNull ClassroomEntity classroomEntity, @NotNull @PathVariable Integer id) {
        classroomEntity.setId(id);
        if (classroomService.updateClassroom(classroomEntity).isPresent()) {
            return ResponseEntity.ok().build();
        } else {
            //更新失败，返回服务器无法处理目标请求
            throw new EntityNotFoundException("Entity Update Failed");
//            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }

    @DeleteMapping(RequestPathName.CLASSROOMS + "/{id}")
    @Validated
    public ResponseEntity<ClassroomEntity> deleteClassroom(@NotNull @PathVariable Integer id) {
        ClassroomEntity classroomEntity = classroomService.findClassroomByClassroomId(id).orElseThrow(EntityNotFoundException::new);
        classroomService.deleteClassroom(classroomEntity);
        return ResponseEntity.ok().build();
    }

}
