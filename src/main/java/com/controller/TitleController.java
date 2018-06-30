package com.controller;

import com.constattribute.RequestPathName;
import com.entity.TitleEntity;
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


@Controller
public class TitleController {
    private Logger logger = LogManager.getLogger(TitleController.class);
    private TitleService titleService;

    @Autowired
    public TitleController(TitleService titleService) {
        this.titleService = titleService;
    }

    @GetMapping(RequestPathName.TITLES)
    public List<TitleEntity> getAllTitle() {
        //TODO 加入缓存控制
        return titleService.findTitles();
    }


    @GetMapping(RequestPathName.TITLES + "/{id}")
    @ResponseBody
    @Validated
    public TitleEntity getTitle(@PathVariable @NotNull Integer id) {
        logger.info("get Title, id: " + id);
        return titleService.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @PostMapping(RequestPathName.TITLES)
    @Validated
    public ResponseEntity<TitleEntity> createTitle(@NotNull TitleEntity titleEntity) throws URISyntaxException {
        TitleEntity createdTitleEntity = titleService.addTitle(titleEntity);
        return ResponseEntity.created(new URI(RequestPathName.TITLES + "/" + createdTitleEntity.getId())).build();
    }

    @PatchMapping(RequestPathName.TITLES + "/{id}")
    @Validated
    public ResponseEntity<TitleEntity> updateTitle(@NotNull TitleEntity titleEntity, @NotNull @PathVariable Integer id) {
        titleEntity.setId(id);
        if (titleService.updateTitle(titleEntity).isPresent()) {
            return ResponseEntity.ok().build();
        } else {
            //更新失败，返回服务器无法处理目标请求
            throw new EntityNotFoundException("Entity Update Failed");
//            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }

    @DeleteMapping(RequestPathName.TITLES + "/{id}")
    @Validated
    public ResponseEntity<TitleEntity> deleteTitle(@NotNull @PathVariable Integer id) {
        TitleEntity titleEntity = titleService.findById(id).orElseThrow(EntityNotFoundException::new);
        titleService.deleteTitle(titleEntity);
        return ResponseEntity.ok().build();
    }

}
