package com.service;

import com.entity.ReplyMissionEntity;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface ReplyMissionService {
    /**
     * 添加replyMission
     *
     * @param replyMission replyMission对象
     * @return 成功返回新的replyMission对象，失败返回null
     */
    @NotNull Optional<ReplyMissionEntity> addReplyMission(@NotNull ReplyMissionEntity replyMission);

    /**
     * 修改回复类任务
     *
     * @param replyMission replyMission中带有旧的回复类任务的id
     * @return 成功返回新的replyMission实体
     */
    @NotNull Optional<ReplyMissionEntity> updateReplyMission(@NotNull ReplyMissionEntity replyMission);

    /**
     * 删除指定的回复类任务
     *
     * @param replyMission 有效的含有id的replyMission实体
     * @return 是否成功
     */
    boolean deleteReplyMission(@NotNull ReplyMissionEntity replyMission);

    /**
     * 根据replyMission的id查找回复类任务
     *
     * @param replyMissionId 回复类任务id
     * @return replymission对象
     */
    @NotNull Optional<ReplyMissionEntity> findReplyMissionByReplyMissionId(int replyMissionId);

}
