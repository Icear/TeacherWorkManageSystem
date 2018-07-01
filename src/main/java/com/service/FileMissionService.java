package com.service;

import com.entity.FileMissionEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface FileMissionService {
    /**
     * 添加fileMission
     *
     * @param fileMission fileMission对象
     * @return 成功返回新的fileMission对象，失败返回null
     */
    @NotNull FileMissionEntity addFileMission(@NotNull FileMissionEntity fileMission);

    /**
     * 修改文件类任务
     *
     * @param fileMission fileMission中带有旧的文件类任务的id
     * @return 成功返回新的fileMission实体
     */
    @NotNull Optional<FileMissionEntity> updateFileMission(@NotNull FileMissionEntity fileMission);

    /**
     * 删除指定的文件类任务
     *
     * @param fileMission 有效的含有id的fileMission实体
     */
    void deleteFileMission(@NotNull FileMissionEntity fileMission);

    /**
     * 根据fileMission的id查找文件类任务
     *
     * @param fileMissionId 文件类任务id
     * @return filemission对象
     */
    @NotNull Optional<FileMissionEntity> findFileMissionByFileMissionId(int fileMissionId);

    /**
     * 查找所有文件类任务
     *
     * @return fileMission对象集合
     */
    @NotNull List<FileMissionEntity> findAllFileMissions();

}
