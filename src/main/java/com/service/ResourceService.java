package com.service;

import com.entity.ResourceEntity;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public interface ResourceService {
    /**
     * 添加新的resource到表中
     *
     * @param resource resource对象
     * @return 成功返回新的resource对象，失败返回null
     */
    @NotNull ResourceEntity addResource(@NotNull ResourceEntity resource);

    /**
     * 更新resource
     *
     * @param resource 新的resource对象，其中带有旧的id
     * @return 成功返回新的resource对象
     */
    @NotNull Optional<ResourceEntity> updateResource(@NotNull ResourceEntity resource);

    /**
     * 删除resource
     *
     * @param resource 有效的含有resourceId的实体
     * @return 是否成功
     */
    boolean deleteResource(@NotNull ResourceEntity resource);

    /**
     * 根据resourceId查找resource实体
     *
     * @param resourceId resourceId
     * @return 成功返回resource实体，失败返回null
     */
    @NotNull Optional<ResourceEntity> findResourceByResourceId(int resourceId);

    /**
     * 根据filemissionId查找指定文件类任务下所有的文件
     *
     * @param fileMissionId 文件类任务id
     * @return 文件实体集合
     */
    @NotNull List<ResourceEntity> findResources(int fileMissionId);
}
