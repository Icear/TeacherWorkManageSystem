package com.service.impl;

import com.dao.MissionEntityDao;
import com.entity.MissionEntity;
import com.service.MissionService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MissionServiceImpl implements MissionService {
    private final MissionEntityDao missionEntityDao;

    @Autowired
    public MissionServiceImpl(MissionEntityDao missionEntityDao) {
        this.missionEntityDao = missionEntityDao;
    }

    @Override
    public @NotNull MissionEntity addMission(@NotNull MissionEntity mission) {
        return missionEntityDao.add(mission);
    }

    @Override
    public @NotNull Optional<MissionEntity> updateMission(@NotNull MissionEntity mission) {
        return Optional.ofNullable(missionEntityDao.edit(mission));
    }

    @Override
    public void deleteMission(@NotNull MissionEntity mission) {
        missionEntityDao.delete(mission);
    }

//    @Override
//    public @NotNull Optional<MissionEntity> closeMission(@NotNull MissionEntity mission) {
//        return Optional.empty();
//    }

    @Override
    public @NotNull Optional<MissionEntity> findMissionByMissionId(int missionId) {
        return Optional.ofNullable(missionEntityDao.find(missionId));
    }

    @Override
    public @NotNull Optional<MissionEntity> findMissionByFileMissionId(int fileMissionId) {
        return Optional.empty();
    }

    @Override
    public @NotNull Optional<MissionEntity> findMissionByReplyMissionId(int replyMissionId) {
        return Optional.empty();
    }

    @Override
    public @NotNull List<MissionEntity> findAllMissions() {
        return missionEntityDao.list();
    }
}
