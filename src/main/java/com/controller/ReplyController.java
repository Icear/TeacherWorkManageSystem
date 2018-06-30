package com.controller;

import com.constattribute.RequestPathName;
import com.entity.ReplyEntity;
import com.service.ReplyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;


@Controller
public class ReplyController {
    private Logger logger = LogManager.getLogger(ReplyController.class);
    private ReplyService replyService;

    @Autowired
    public ReplyController(ReplyService replyService) {
        this.replyService = replyService;
    }

    @GetMapping(RequestPathName.REPLIES)
    public ResponseEntity getAllReply() {
//        //TODO 加入缓存控制
//        return replyService.findReplies();
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
    }


    @GetMapping(RequestPathName.REPLIES + "/{id}")
    @ResponseBody
    @Validated
    public ReplyEntity getReply(@PathVariable @NotNull Integer id) {
        logger.info("get Reply, id: " + id);
        return replyService.findReplyByReplyId(id).orElseThrow(EntityNotFoundException::new);
    }

    @PostMapping(RequestPathName.REPLIES)
    @Validated
    public ResponseEntity<ReplyEntity> createReply(@NotNull ReplyEntity replyEntity) throws URISyntaxException {
        ReplyEntity createdReplyEntity = replyService.addReply(replyEntity);
        return ResponseEntity.created(new URI(RequestPathName.REPLIES + "/" + createdReplyEntity.getId())).build();
    }

    @PatchMapping(RequestPathName.REPLIES + "/{id}")
    @Validated
    public ResponseEntity<ReplyEntity> updateReply(@NotNull ReplyEntity replyEntity, @NotNull @PathVariable Integer id) {
//        replyEntity.setId(id);
//        if (replyService.updateReply(replyEntity).isPresent()) {
//            return ResponseEntity.ok().build();
//        } else {
//            //更新失败，返回服务器无法处理目标请求
//            throw new EntityNotFoundException("Entity Update Failed");
////            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
//        }
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
    }

    @DeleteMapping(RequestPathName.REPLIES + "/{id}")
    @Validated
    public ResponseEntity<ReplyEntity> deleteReply(@NotNull @PathVariable Integer id) {
        ReplyEntity replyEntity = replyService.findReplyByReplyId(id).orElseThrow(EntityNotFoundException::new);
        replyService.recallReply(replyEntity);
        return ResponseEntity.ok().build();
    }

}
