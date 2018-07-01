package com.dao;

import com.entity.ResourceEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 * ResourceEntityDao 类
 * 相对于父类新增根据资源名称查找资源任务实体的方法
 */
@Repository
public class ResourceEntityDao extends GenericDao<ResourceEntity> {

    private static Logger logger = LogManager.getLogger(ResourceEntityDao.class);
    public ResourceEntityDao() {
    }
    /**
     * 采用资源名称查找资源任务实体
     * 资源任务实体可空 资源任务名称不可空
     * @param name 资源任务名称
     * @return 资源任务实体
     */
    public @Nullable ResourceEntity findByName(@NotNull String name){
        String jpql = "FROM ResourceEntity r WHERE r.name=:name";
        Query query = getEntityManager().createQuery(jpql);
        query.setParameter("name",name);
        ResourceEntity resourceEntity;
        try {
            resourceEntity = (ResourceEntity) query.getSingleResult();
        } catch (NoResultException e) {
            logger.info("未找到实体");
            return null;
        }

        return  resourceEntity;
    }
}
