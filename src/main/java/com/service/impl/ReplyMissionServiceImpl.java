package com.service.impl;

import com.dao.ReplyMissionEntityDao;
import com.entity.ReplyMissionEntity;
import com.service.ReplyMissionService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReplyMissionServiceImpl implements ReplyMissionService {
    private final ReplyMissionEntityDao replyMissionEntityDao;

    @Autowired
    public ReplyMissionServiceImpl(ReplyMissionEntityDao replyMissionEntityDao) {
        this.replyMissionEntityDao = replyMissionEntityDao;
    }

    @Override
    public @NotNull ReplyMissionEntity addReplyMission(@NotNull ReplyMissionEntity replyMission) {
        return replyMissionEntityDao.add(replyMission);
    }

    @Override
    public @NotNull Optional<ReplyMissionEntity> updateReplyMission(@NotNull ReplyMissionEntity replyMission) {
        return Optional.ofNullable(replyMissionEntityDao.edit(replyMission));
    }

    @Override
    public void deleteReplyMission(@NotNull ReplyMissionEntity replyMission) {
        replyMissionEntityDao.delete(replyMission);
    }

    @Override
    public @NotNull Optional<ReplyMissionEntity> findReplyMissionByReplyMissionId(int replyMissionId) {
        return Optional.ofNullable(replyMissionEntityDao.find(replyMissionId));
    }

    @Override
    public @NotNull List<ReplyMissionEntity> findAllReplyMissions() {
        return replyMissionEntityDao.list();
    }
}
