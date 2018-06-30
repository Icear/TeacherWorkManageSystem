package com.controller;

import com.constattribute.RequestPathName;
import com.entity.MissionEntity;
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


@Controller
public class MissionController {
    private Logger logger = LogManager.getLogger(MissionController.class);
    private MissionService missionService;

    @Autowired
    public MissionController(MissionService missionService) {
        this.missionService = missionService;
    }

    @GetMapping(RequestPathName.MISSIONS)
    public List<MissionEntity> getAllMission() {
        //TODO 加入缓存控制
        return missionService.findAllMissions();
    }


    @GetMapping(RequestPathName.MISSIONS + "/{id}")
    @ResponseBody
    @Validated
    public MissionEntity getMission(@PathVariable @NotNull Integer id) {
        logger.info("get Mission, id: " + id);
        return missionService.findMissionByMissionId(id).orElseThrow(EntityNotFoundException::new);
    }

    @PostMapping(RequestPathName.MISSIONS)
    @Validated
    public ResponseEntity<MissionEntity> createMission(@NotNull MissionEntity missionEntity) throws URISyntaxException {
        MissionEntity createdMissionEntity = missionService.addMission(missionEntity);
        return ResponseEntity.created(new URI(RequestPathName.MISSIONS + "/" + createdMissionEntity.getId())).build();
    }

    @PatchMapping(RequestPathName.MISSIONS + "/{id}")
    @Validated
    public ResponseEntity<MissionEntity> updateMission(@NotNull MissionEntity missionEntity, @NotNull @PathVariable Integer id) {
        missionEntity.setId(id);
        if (missionService.updateMission(missionEntity).isPresent()) {
            return ResponseEntity.ok().build();
        } else {
            //更新失败，返回服务器无法处理目标请求
            throw new EntityNotFoundException("Entity Update Failed");
//            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }

    @DeleteMapping(RequestPathName.MISSIONS + "/{id}")
    @Validated
    public ResponseEntity<MissionEntity> deleteMission(@NotNull @PathVariable Integer id) {
        MissionEntity missionEntity = missionService.findMissionByMissionId(id).orElseThrow(EntityNotFoundException::new);
        missionService.deleteMission(missionEntity);
        return ResponseEntity.ok().build();
    }

}
