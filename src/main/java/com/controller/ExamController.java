package com.controller;

import com.annotation.Authority;
import com.constattribute.RequestPathName;
import com.entity.ExamEntity;
import com.identity.Identity;
import com.service.ExamService;
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
public class ExamController {
    private Logger logger = LogManager.getLogger(ExamController.class);
    private ExamService examService;

    @Autowired
    public ExamController(ExamService examService) {
        this.examService = examService;
    }

    @Authority(role = Identity.User)
    @Authority(role = Identity.Administrator)
    @GetMapping(RequestPathName.EXAMS)
    public List<ExamEntity> getAllExam() {
        //TODO 加入缓存控制
        logger.info("get all exam");
        return examService.findAllExams();
    }


    @Authority(role = Identity.User)
    @Authority(role = Identity.Administrator)
    @GetMapping(RequestPathName.EXAMS + "/{id}")
    @ResponseBody
    @Validated
    public ExamEntity getExam(@PathVariable @NotNull Integer id) {
        logger.info("get Exam, id: " + id);
        return examService.findExamByExamId(id).orElseThrow(EntityNotFoundException::new);
    }

    @Authority(role = Identity.Administrator)
    @PostMapping(RequestPathName.EXAMS)
    @Validated
    public ResponseEntity<ExamEntity> createExam(@NotNull ExamEntity examEntity) throws URISyntaxException {
        logger.debug(examEntity);
        ExamEntity createdExamEntity = examService.addExam(examEntity);
        logger.info("exam " + createdExamEntity.getId() + " created");
        logger.debug(createdExamEntity);
        return ResponseEntity.created(new URI(RequestPathName.EXAMS + "/" + createdExamEntity.getId())).build();
    }

    @Authority(role = Identity.Administrator)
    @PatchMapping(RequestPathName.EXAMS + "/{id}")
    @Validated
    public ResponseEntity<ExamEntity> updateExam(@NotNull ExamEntity examEntity, @NotNull @PathVariable Integer id) {
        logger.debug(examEntity);
        examEntity.setId(id);
        Optional<ExamEntity> updatedExamEntity = examService.updateExam(examEntity);
        if (updatedExamEntity.isPresent()) {
            logger.info("update exam " + id);
            logger.debug(updatedExamEntity.get());
            return ResponseEntity.ok().build();
        } else {
            //更新失败，返回服务器无法处理目标请求
            throw new EntityNotFoundException("Entity Update Failed");
//            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }

    @Authority(role = Identity.Administrator)
    @DeleteMapping(RequestPathName.EXAMS + "/{id}")
    @Validated
    public ResponseEntity<ExamEntity> deleteExam(@NotNull @PathVariable Integer id) {
        ExamEntity examEntity = examService.findExamByExamId(id).orElseThrow(EntityNotFoundException::new);
        examService.deleteExam(examEntity);
        logger.info("exam " + id + " removed");
        return ResponseEntity.ok().build();
    }

}
