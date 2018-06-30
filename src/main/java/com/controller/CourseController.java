package com.controller;

import com.annotation.Authority;
import com.constattribute.RequestPathName;
import com.entity.CourseEntity;
import com.identity.Identity;
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
import java.util.Optional;


@Controller
public class CourseController {
    private Logger logger = LogManager.getLogger(CourseController.class);
    private CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @Authority(role = Identity.User)
    @Authority(role = Identity.Administrator)
    @GetMapping(RequestPathName.COURSES)
    public List<CourseEntity> getAllCourse() {
        //TODO 加入缓存控制
        return courseService.findCourses();
    }

    @Authority(role = Identity.User)
    @Authority(role = Identity.Administrator)
    @GetMapping(RequestPathName.COURSES + "/{id}")
    @ResponseBody
    @Validated
    public CourseEntity getCourse(@PathVariable @NotNull Integer id) {
        logger.info("get Course, id: " + id);
        return courseService.findCourseByCourseId(id).orElseThrow(EntityNotFoundException::new);
    }

    @Authority(role = Identity.Administrator)
    @PostMapping(RequestPathName.COURSES)
    @Validated
    public ResponseEntity<CourseEntity> createCourse(@NotNull CourseEntity courseEntity) throws URISyntaxException {
        logger.debug(courseEntity);
        CourseEntity createdCourseEntity = courseService.addCourse(courseEntity);
        logger.info("course " + createdCourseEntity.getId() + " created");
        logger.debug(createdCourseEntity);
        return ResponseEntity.created(new URI(RequestPathName.COURSES + "/" + createdCourseEntity.getId())).build();
    }

    @Authority(role = Identity.Administrator)
    @PatchMapping(RequestPathName.COURSES + "/{id}")
    @Validated
    public ResponseEntity<CourseEntity> updateCourse(@NotNull CourseEntity courseEntity, @NotNull @PathVariable Integer id) {
        logger.debug(courseEntity);
        courseEntity.setId(id);
        Optional<CourseEntity> updatedCourseEntity = courseService.updateCourse(courseEntity);
        if (updatedCourseEntity.isPresent()) {
            logger.info("course " + id + "updated");
            logger.debug(updatedCourseEntity.get());
            return ResponseEntity.ok().build();
        } else {
            //更新失败，返回服务器无法处理目标请求
            throw new EntityNotFoundException("Entity Update Failed");
//            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }

    @Authority(role = Identity.Administrator)
    @DeleteMapping(RequestPathName.COURSES + "/{id}")
    @Validated
    public ResponseEntity<CourseEntity> deleteCourse(@NotNull @PathVariable Integer id) {
        CourseEntity courseEntity = courseService.findCourseByCourseId(id).orElseThrow(EntityNotFoundException::new);
        courseService.deleteCourse(courseEntity);
        logger.info("course " + id + "removed");
        return ResponseEntity.ok().build();
    }

}
