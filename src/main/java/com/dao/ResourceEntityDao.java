package com.dao;

import com.entity.ResourceEntity;

import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 * ResourceEntityDao 类
 * 相对于父类新增根据资源名称查找资源任务实体的方法
 */
public class ResourceEntityDao extends GenericDao<ResourceEntity> {
    public ResourceEntityDao() {
    }
    /**
     * 采用资源名称查找资源任务实体
     * @param name 资源任务名称
     * @return 资源任务实体
     */
    public ResourceEntity findByName(String name){
        String jpql = "FROM ResourceEntity r WHERE r.name=:name";
        Query query = getEntityManager().createQuery(jpql);
        query.setParameter("name",name);
        ResourceEntity resourceEntity = null;
        try {
            resourceEntity = (ResourceEntity) query.getSingleResult();
        } catch (NoResultException e){
            //采用统一异常处理
        }
        return  resourceEntity;
    }
}
