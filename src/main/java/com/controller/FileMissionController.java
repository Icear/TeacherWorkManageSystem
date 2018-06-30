package com.controller;

import com.constattribute.RequestPathName;
import com.entity.FileMissionEntity;
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


@Controller
public class FileMissionController {
    private Logger logger = LogManager.getLogger(FileMissionController.class);
    private FileMissionService fileMissionService;

    @Autowired
    public FileMissionController(FileMissionService fileMissionService) {
        this.fileMissionService = fileMissionService;
    }

    @GetMapping(RequestPathName.FILE_MISSIONS)
    public List<FileMissionEntity> getAllFileMission() {
        //TODO 加入缓存控制
        return fileMissionService.findAllFileMissions();
    }


    @GetMapping(RequestPathName.FILE_MISSIONS + "/{id}")
    @ResponseBody
    @Validated
    public FileMissionEntity getFileMission(@PathVariable @NotNull Integer id) {
        logger.info("get FileMission, id: " + id);
        return fileMissionService.findFileMissionByFileMissionId(id).orElseThrow(EntityNotFoundException::new);
    }

    @PostMapping(RequestPathName.FILE_MISSIONS)
    @Validated
    public ResponseEntity<FileMissionEntity> createFileMission(@NotNull FileMissionEntity fileMissionEntity) throws URISyntaxException {
        FileMissionEntity createdFileMissionEntity = fileMissionService.addFileMission(fileMissionEntity);
        return ResponseEntity.created(new URI(RequestPathName.FILE_MISSIONS + "/" + createdFileMissionEntity.getId())).build();
    }

    @PatchMapping(RequestPathName.FILE_MISSIONS + "/{id}")
    @Validated
    public ResponseEntity<FileMissionEntity> updateFileMission(@NotNull FileMissionEntity fileMissionEntity, @NotNull @PathVariable Integer id) {
        fileMissionEntity.setId(id);
        if (fileMissionService.updateFileMission(fileMissionEntity).isPresent()) {
            return ResponseEntity.ok().build();
        } else {
            //更新失败，返回服务器无法处理目标请求
            throw new EntityNotFoundException("Entity Update Failed");
//            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }

    @DeleteMapping(RequestPathName.FILE_MISSIONS + "/{id}")
    @Validated
    public ResponseEntity<FileMissionEntity> deleteFileMission(@NotNull @PathVariable Integer id) {
        FileMissionEntity fileMissionEntity = fileMissionService.findFileMissionByFileMissionId(id).orElseThrow(EntityNotFoundException::new);
        fileMissionService.deleteFileMission(fileMissionEntity);
        return ResponseEntity.ok().build();
    }

}
