package com.service;

import com.entity.MissionEntity;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public interface MissionService {
    /**
     * 管理员创建指定类型任务
     *
     * @param mission mission对象
     * @return 成功返回新的mission实体，失败返回null
     */
    @NotNull MissionEntity addMission(@NotNull MissionEntity mission);

    /**
     * 修改指定任务信息
     *
     * @param mission mission中需带有旧的任务信息的id
     * @return 成功返回新的mission实体
     */
    @NotNull Optional<MissionEntity> updateMission(@NotNull MissionEntity mission);

    /**
     * 根据id删除mission
     *
     * @param mission 有效的含id的mission类
     */
    void deleteMission(@NotNull MissionEntity mission);

    // TODO: 2018/6/19 关闭mission应该是修改状态吧，然后把updatable=false？这种？所以会返回实体吧？

//    /**
//     * 关闭指定任务
//     *
//     * @param mission 有效的含id的mission类
//     * @return 返回新的mission实体
//     */
//    @NotNull Optional<MissionEntity> closeMission(@NotNull MissionEntity mission);

    /**
     * 根据missionId查找mission
     *
     * @param missionId missionId
     * @return mission对象
     */
    @NotNull Optional<MissionEntity> findMissionByMissionId(int missionId);

    /**
     * 根据文件mission id查找mission
     *
     * @param fileMissionId fileMissionId
     * @return mission对象
     */
    @NotNull Optional<MissionEntity> findMissionByFileMissionId(int fileMissionId);

    /**
     * 根据回复mission id查找mission
     *
     * @param replyMissionId replyMissionId
     * @return mission对象
     */
    @NotNull Optional<MissionEntity> findMissionByReplyMissionId(int replyMissionId);

    /**
     * 查询所有任务
     *
     * @return 所有任务集合
     */
    @NotNull List<MissionEntity> findAllMissions();
}
