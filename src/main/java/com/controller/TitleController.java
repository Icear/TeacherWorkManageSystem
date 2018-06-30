package com.controller;

import com.annotation.Authority;
import com.constattribute.RequestPathName;
import com.entity.TitleEntity;
import com.identity.Identity;
import com.service.TitleService;
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
public class TitleController {
    private Logger logger = LogManager.getLogger(TitleController.class);
    private TitleService titleService;

    @Autowired
    public TitleController(TitleService titleService) {
        this.titleService = titleService;
    }

    @Authority(role = Identity.User)
    @Authority(role = Identity.Administrator)
    @GetMapping(RequestPathName.TITLES)
    public List<TitleEntity> getAllTitle() {
        //TODO 加入缓存控制
        logger.info("get all titles");
        return titleService.findTitles();
    }

    @Authority(role = Identity.User)
    @Authority(role = Identity.Administrator)
    @GetMapping(RequestPathName.TITLES + "/{id}")
    @ResponseBody
    @Validated
    public TitleEntity getTitle(@PathVariable @NotNull Integer id) {
        logger.info("get Title, id: " + id);
        return titleService.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Authority(role = Identity.Administrator)
    @PostMapping(RequestPathName.TITLES)
    @Validated
    public ResponseEntity<TitleEntity> createTitle(@NotNull TitleEntity titleEntity) throws URISyntaxException {
        logger.debug(titleEntity);
        TitleEntity createdTitleEntity = titleService.addTitle(titleEntity);
        logger.info("title " + createdTitleEntity.getId() + " created");
        logger.debug(createdTitleEntity);
        return ResponseEntity.created(new URI(RequestPathName.TITLES + "/" + createdTitleEntity.getId())).build();
    }

    @Authority(role = Identity.Administrator)
    @PatchMapping(RequestPathName.TITLES + "/{id}")
    @Validated
    public ResponseEntity<TitleEntity> updateTitle(@NotNull TitleEntity titleEntity, @NotNull @PathVariable Integer id) {
        logger.debug(titleEntity);
        titleEntity.setId(id);
        Optional<TitleEntity> updatedTitleEntity = titleService.updateTitle(titleEntity);
        if (updatedTitleEntity.isPresent()) {
            logger.info("title " + id + "updated");
            logger.debug(updatedTitleEntity.get());
            return ResponseEntity.ok().build();
        } else {
            //更新失败，返回服务器无法处理目标请求
            throw new EntityNotFoundException("Entity Update Failed");
//            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }

    @Authority(role = Identity.Administrator)
    @DeleteMapping(RequestPathName.TITLES + "/{id}")
    @Validated
    public ResponseEntity<TitleEntity> deleteTitle(@NotNull @PathVariable Integer id) {
        TitleEntity titleEntity = titleService.findById(id).orElseThrow(EntityNotFoundException::new);
        titleService.deleteTitle(titleEntity);
        logger.info("title " + id + " removed");
        return ResponseEntity.ok().build();
    }

}
