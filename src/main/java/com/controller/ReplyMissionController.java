package com.controller;

import com.constattribute.RequestPathName;
import com.entity.ReplyMissionEntity;
import com.service.ReplyMissionService;
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
public class ReplyMissionController {
    private Logger logger = LogManager.getLogger(ReplyMissionController.class);
    private ReplyMissionService replyMissionService;

    @Autowired
    public ReplyMissionController(ReplyMissionService replyMissionService) {
        this.replyMissionService = replyMissionService;
    }

    @GetMapping(RequestPathName.REPLY_MISSIONS)
    public List<ReplyMissionEntity> getAllReplyMission() {
        //TODO 加入缓存控制
        return replyMissionService.findAllReplyMissions();
    }


    @GetMapping(RequestPathName.REPLY_MISSIONS + "/{id}")
    @ResponseBody
    @Validated
    public ReplyMissionEntity getReplyMission(@PathVariable @NotNull Integer id) {
        logger.info("get ReplyMission, id: " + id);
        return replyMissionService.findReplyMissionByReplyMissionId(id).orElseThrow(EntityNotFoundException::new);
    }

    @PostMapping(RequestPathName.REPLY_MISSIONS)
    @Validated
    public ResponseEntity<ReplyMissionEntity> createReplyMission(@NotNull ReplyMissionEntity replyMissionEntity) throws URISyntaxException {
        ReplyMissionEntity createdReplyMissionEntity = replyMissionService.addReplyMission(replyMissionEntity);
        return ResponseEntity.created(new URI(RequestPathName.REPLY_MISSIONS + "/" + createdReplyMissionEntity.getId())).build();
    }

    @PatchMapping(RequestPathName.REPLY_MISSIONS + "/{id}")
    @Validated
    public ResponseEntity<ReplyMissionEntity> updateReplyMission(@NotNull ReplyMissionEntity replyMissionEntity, @NotNull @PathVariable Integer id) {
        replyMissionEntity.setId(id);
        if (replyMissionService.updateReplyMission(replyMissionEntity).isPresent()) {
            return ResponseEntity.ok().build();
        } else {
            //更新失败，返回服务器无法处理目标请求
            throw new EntityNotFoundException("Entity Update Failed");
//            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }

    @DeleteMapping(RequestPathName.REPLY_MISSIONS + "/{id}")
    @Validated
    public ResponseEntity<ReplyMissionEntity> deleteReplyMission(@NotNull @PathVariable Integer id) {
        ReplyMissionEntity replyMissionEntity = replyMissionService.findReplyMissionByReplyMissionId(id).orElseThrow(EntityNotFoundException::new);
        replyMissionService.deleteReplyMission(replyMissionEntity);
        return ResponseEntity.ok().build();
    }

}
