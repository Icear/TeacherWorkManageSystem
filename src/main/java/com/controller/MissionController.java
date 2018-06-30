package com.controller;

import com.annotation.Authority;
import com.constattribute.RequestPathName;
import com.entity.MissionEntity;
import com.identity.Identity;
import com.service.MissionService;
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
public class MissionController {
    private Logger logger = LogManager.getLogger(MissionController.class);
    private MissionService missionService;

    @Autowired
    public MissionController(MissionService missionService) {
        this.missionService = missionService;
    }

    @Authority(role = Identity.User)
    @Authority(role = Identity.Administrator)
    @GetMapping(RequestPathName.MISSIONS)
    public List<MissionEntity> getAllMission() {
        //TODO 加入缓存控制
        return missionService.findAllMissions();
    }


    @Authority(role = Identity.User)
    @Authority(role = Identity.Administrator)
    @GetMapping(RequestPathName.MISSIONS + "/{id}")
    @ResponseBody
    @Validated
    public MissionEntity getMission(@PathVariable @NotNull Integer id) {
        logger.info("get Mission, id: " + id);
        return missionService.findMissionByMissionId(id).orElseThrow(EntityNotFoundException::new);
    }

    @Authority(role = Identity.Administrator)
    @PostMapping(RequestPathName.MISSIONS)
    @Validated
    public ResponseEntity<MissionEntity> createMission(@NotNull MissionEntity missionEntity) throws URISyntaxException {
        logger.debug(missionEntity);
        MissionEntity createdMissionEntity = missionService.addMission(missionEntity);
        logger.info("fileMission " + createdMissionEntity.getId() + " created");
        logger.debug(createdMissionEntity);
        return ResponseEntity.created(new URI(RequestPathName.MISSIONS + "/" + createdMissionEntity.getId())).build();
    }

    @Authority(role = Identity.Administrator)
    @PatchMapping(RequestPathName.MISSIONS + "/{id}")
    @Validated
    public ResponseEntity<MissionEntity> updateMission(@NotNull MissionEntity missionEntity, @NotNull @PathVariable Integer id) {
        logger.debug(missionEntity);
        missionEntity.setId(id);
        Optional<MissionEntity> updatedMissionEntity = missionService.updateMission(missionEntity);
        if (updatedMissionEntity.isPresent()) {
            logger.info("mission " + id + " updated");
            logger.debug(updatedMissionEntity.get());
            return ResponseEntity.ok().build();
        } else {
            //更新失败，返回服务器无法处理目标请求
            throw new EntityNotFoundException("Entity Update Failed");
//            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }

    @Authority(role = Identity.Administrator)
    @DeleteMapping(RequestPathName.MISSIONS + "/{id}")
    @Validated
    public ResponseEntity<MissionEntity> deleteMission(@NotNull @PathVariable Integer id) {
        MissionEntity missionEntity = missionService.findMissionByMissionId(id).orElseThrow(EntityNotFoundException::new);
        missionService.deleteMission(missionEntity);
        logger.info("mission " + id + "deleted");
        return ResponseEntity.ok().build();
    }

}
