package com.controller;

import com.annotation.Authority;
import com.constattribute.RequestPathName;
import com.entity.FileMissionEntity;
import com.identity.Identity;
import com.service.FileMissionService;
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
public class FileMissionController {
    private Logger logger = LogManager.getLogger(FileMissionController.class);
    private FileMissionService fileMissionService;

    @Autowired
    public FileMissionController(FileMissionService fileMissionService) {
        this.fileMissionService = fileMissionService;
    }

    @Authority(role = Identity.User)
    @Authority(role = Identity.Administrator)
    @GetMapping(RequestPathName.FILE_MISSIONS)
    public List<FileMissionEntity> getAllFileMission() {
        //TODO 加入缓存控制
        logger.info("get all fileMission");
        return fileMissionService.findAllFileMissions();
    }

    @Authority(role = Identity.User)
    @Authority(role = Identity.Administrator)
    @GetMapping(RequestPathName.FILE_MISSIONS + "/{id}")
    @ResponseBody
    @Validated
    public FileMissionEntity getFileMission(@PathVariable @NotNull Integer id) {
        logger.info("get FileMission, id: " + id);
        return fileMissionService.findFileMissionByFileMissionId(id).orElseThrow(EntityNotFoundException::new);
    }

    @Authority(role = Identity.User)
    @Authority(role = Identity.Administrator)
    @PostMapping(RequestPathName.FILE_MISSIONS)
    @Validated
    public ResponseEntity<FileMissionEntity> createFileMission(@NotNull FileMissionEntity fileMissionEntity) throws URISyntaxException {
        logger.debug(fileMissionEntity);
        FileMissionEntity createdFileMissionEntity = fileMissionService.addFileMission(fileMissionEntity);
        logger.info("fileMission " + createdFileMissionEntity.getId() + "created");
        logger.debug(createdFileMissionEntity);
        return ResponseEntity.created(new URI(RequestPathName.FILE_MISSIONS + "/" + createdFileMissionEntity.getId())).build();
    }

    @Authority(role = Identity.User)
    @Authority(role = Identity.Administrator)
    @PatchMapping(RequestPathName.FILE_MISSIONS + "/{id}")
    @Validated
    public ResponseEntity<FileMissionEntity> updateFileMission(@NotNull FileMissionEntity fileMissionEntity, @NotNull @PathVariable Integer id) {
        logger.debug(fileMissionEntity);
        fileMissionEntity.setId(id);
        Optional<FileMissionEntity> updatedFileMissionEntity = fileMissionService.updateFileMission(fileMissionEntity);
        if (updatedFileMissionEntity.isPresent()) {
            logger.info("fileMission " + id + " updated");
            logger.debug(updatedFileMissionEntity.get());
            return ResponseEntity.ok().build();
        } else {
            //更新失败，返回服务器无法处理目标请求
            throw new EntityNotFoundException("Entity Update Failed");
//            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }

    @Authority(role = Identity.User)
    @Authority(role = Identity.Administrator)
    @DeleteMapping(RequestPathName.FILE_MISSIONS + "/{id}")
    @Validated
    public ResponseEntity<FileMissionEntity> deleteFileMission(@NotNull @PathVariable Integer id) {
        FileMissionEntity fileMissionEntity = fileMissionService.findFileMissionByFileMissionId(id).orElseThrow(EntityNotFoundException::new);
        fileMissionService.deleteFileMission(fileMissionEntity);
        logger.info("fileMission " + id + "deleted");
        return ResponseEntity.ok().build();
    }

}
