package com.service.impl;

import com.dao.ResourceEntityDao;
import com.entity.ResourceEntity;
import com.service.ResourceService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ResourceServiceImpl implements ResourceService {
    private final ResourceEntityDao resourceEntityDao;

    @Autowired
    public ResourceServiceImpl(ResourceEntityDao resourceEntityDao) {
        this.resourceEntityDao = resourceEntityDao;
    }

    @Override
    public @NotNull ResourceEntity addResource(@NotNull ResourceEntity resource) {
        return resourceEntityDao.add(resource);
    }

    @Override
    public @NotNull Optional<ResourceEntity> updateResource(@NotNull ResourceEntity resource) {
        return Optional.ofNullable(resourceEntityDao.edit(resource));
    }

    @Override
    public void deleteResource(@NotNull ResourceEntity resource) {
        resourceEntityDao.delete(resource);
    }

    @Override
    public @NotNull Optional<ResourceEntity> findResourceByResourceId(int resourceId) {
        return Optional.ofNullable(resourceEntityDao.find(resourceId));
    }

    @Override
    public @NotNull List<ResourceEntity> findResources(int fileMissionId) {
        return resourceEntityDao.list();
    }
}
