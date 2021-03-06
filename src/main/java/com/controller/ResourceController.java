package com.controller;

import com.annotation.Authority;
import com.constattribute.RequestPathName;
import com.entity.ResourceEntity;
import com.identity.Identity;
import com.service.ResourceService;
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
public class ResourceController {
    private Logger logger = LogManager.getLogger(ResourceController.class);
    private ResourceService resourceService;

    @Autowired
    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @Authority(role = Identity.User)
    @Authority(role = Identity.Administrator)
    @GetMapping(RequestPathName.RESOURCES)
    public ResponseEntity getAllResource() {
//        //TODO 加入缓存控制
//        return resourceService.findResources();
        logger.info("get all resources");
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
    }

    @Authority(role = Identity.User)
    @Authority(role = Identity.Administrator)
    @GetMapping(RequestPathName.RESOURCES + "/{id}")
    @ResponseBody
    @Validated
    public ResourceEntity getResource(@PathVariable @NotNull Integer id) {
        logger.info("get Resource, id: " + id);
        return resourceService.findResourceByResourceId(id).orElseThrow(EntityNotFoundException::new);
    }

    @Authority(role = Identity.User)
    @Authority(role = Identity.Administrator)
    @PostMapping(RequestPathName.RESOURCES)
    @Validated
    public ResponseEntity<ResourceEntity> createResource(@NotNull ResourceEntity resourceEntity) throws URISyntaxException {
        logger.debug(resourceEntity);
        ResourceEntity createdResourceEntity = resourceService.addResource(resourceEntity);
        logger.info("resource " + createdResourceEntity.getId() + " created");
        logger.debug(createdResourceEntity);
        return ResponseEntity.created(new URI(RequestPathName.RESOURCES + "/" + createdResourceEntity.getId())).build();
    }

    @Authority(role = Identity.User)
    @Authority(role = Identity.Administrator)
    @PatchMapping(RequestPathName.RESOURCES + "/{id}")
    @Validated
    public ResponseEntity updateResource(@NotNull ResourceEntity resourceEntity, @NotNull @PathVariable Integer id) {
//        resourceEntity.setId(id);
//        if (resourceService.updateResource(resourceEntity).isPresent()) {
//            return ResponseEntity.ok().build();
//        } else {
//            //更新失败，返回服务器无法处理目标请求
//            throw new EntityNotFoundException("Entity Update Failed");
////            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
//        }
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
    }

    @Authority(role = Identity.User)
    @Authority(role = Identity.Administrator)
    @DeleteMapping(RequestPathName.RESOURCES + "/{id}")
    @Validated
    public ResponseEntity<ResourceEntity> deleteResource(@NotNull @PathVariable Integer id) {
        ResourceEntity resourceEntity = resourceService.findResourceByResourceId(id).orElseThrow(EntityNotFoundException::new);
        resourceService.deleteResource(resourceEntity);
        logger.info("resource " + id + " removed");
        return ResponseEntity.ok().build();
    }

}
