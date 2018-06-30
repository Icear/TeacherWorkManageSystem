package com.controller;

import com.constattribute.RequestPathName;
import com.entity.CourseEntity;
import com.service.CourseService;
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
public class CourseController {
    private Logger logger = LogManager.getLogger(CourseController.class);
    private CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping(RequestPathName.COURSES)
    public List<CourseEntity> getAllCourse() {
        //TODO 加入缓存控制
        return courseService.findCourses();
    }


    @GetMapping(RequestPathName.COURSES + "/{id}")
    @ResponseBody
    @Validated
    public CourseEntity getCourse(@PathVariable @NotNull Integer id) {
        logger.info("get Course, id: " + id);
        return courseService.findCourseByCourseId(id).orElseThrow(EntityNotFoundException::new);
    }

    @PostMapping(RequestPathName.COURSES)
    @Validated
    public ResponseEntity<CourseEntity> createCourse(@NotNull CourseEntity courseEntity) throws URISyntaxException {
        CourseEntity createdCourseEntity = courseService.addCourse(courseEntity);
        return ResponseEntity.created(new URI(RequestPathName.COURSES + "/" + createdCourseEntity.getId())).build();
    }

    @PatchMapping(RequestPathName.COURSES + "/{id}")
    @Validated
    public ResponseEntity<CourseEntity> updateCourse(@NotNull CourseEntity courseEntity, @NotNull @PathVariable Integer id) {
        courseEntity.setId(id);
        if (courseService.updateCourse(courseEntity).isPresent()) {
            return ResponseEntity.ok().build();
        } else {
            //更新失败，返回服务器无法处理目标请求
            throw new EntityNotFoundException("Entity Update Failed");
//            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }

    @DeleteMapping(RequestPathName.COURSES + "/{id}")
    @Validated
    public ResponseEntity<CourseEntity> deleteCourse(@NotNull @PathVariable Integer id) {
        CourseEntity courseEntity = courseService.findCourseByCourseId(id).orElseThrow(EntityNotFoundException::new);
        courseService.deleteCourse(courseEntity);
        return ResponseEntity.ok().build();
    }

}
