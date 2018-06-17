package com.dao;

import com.entity.FileMissionEntity;

import javax.persistence.NoResultException;
import javax.persistence.Query;
/**
 * FileMssionEntityDao 类
 * 相对于父类新增根据文件任务名称查找文件任务实体的方法
 */
public class FileMissionEntityDao extends GenericDao<FileMissionEntity> {
    public FileMissionEntityDao() {
    }

    /**
     * 采用文件任务名称查找文件任务实体
     * @param name 文件任务名称
     * @return 文件任务实体
     */
    public FileMissionEntity findByName(String name){
        String jpql = "FROM FileMissionEntity f WHERE f.name=:name";
        Query query = getEntityManager().createQuery(jpql);
        query.setParameter("name",name);
        FileMissionEntity fileMissionEntity = null;
        try {
            fileMissionEntity = (FileMissionEntity) query.getSingleResult();
        } catch (NoResultException e){
            //采用统一异常处理
        }
        return  fileMissionEntity;
    }
}
