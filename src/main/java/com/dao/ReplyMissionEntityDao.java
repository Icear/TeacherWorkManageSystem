package com.dao;

import com.entity.ReplyMissionEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 * ReplyMssionEntityDao 类
 * 相对于父类新增根据回复任务名称查找回复任务实体的方法
 */
@Repository
public class ReplyMissionEntityDao extends GenericDao<ReplyMissionEntity> {
    public ReplyMissionEntityDao() {
    }
    /**
     * 采用回复任务名称查找回复任务实体
     * @param name 回复任务名称
     * @return 回复任务实体
     */
    public ReplyMissionEntity findByName(String name){
        String jpql = "FROM ReplyMissionEntity r WHERE r.name=:name";
        Query query = getEntityManager().createQuery(jpql);
        query.setParameter("name",name);
        ReplyMissionEntity replyMissionEntity = null;
        try {
            replyMissionEntity = (ReplyMissionEntity) query.getSingleResult();
        } catch (NoResultException e){
            //采用统一异常处理
        }
        return  replyMissionEntity;
    }
}
