package com.dao;

import com.entity.MissionEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;


/**
 * MissionEntityDao 类
 * 继承于父类的方法 无新增方法
 */
@Repository
public class MissionEntityDao extends GenericDao<MissionEntity> {

    private static Logger logger = LogManager.getLogger(MissionEntityDao.class);

    public MissionEntityDao() {
    }

    /**
     * 根据MissionEntity里 replyMisssionEntity的id查找MissionEntity实体
     * @param id 回复实体的id
     * @return 任务实体
     */
    public @Nullable MissionEntity findByReplyMissionId(int id){
        String jpql = "SELECT r.missionEntity FROM ReplyMissionEntity r WHERE r.id=:id";
        Query query = getEntityManager().createQuery(jpql);
        query.setParameter("id",id);
        MissionEntity missionEntity;
        try {
            missionEntity = (MissionEntity) query.getSingleResult();
        } catch (NoResultException e) {
            logger.info("未找到实体");
            return null;
        }

        return  missionEntity;
    }

    /**
     * 根据MissionEntity里 fileMisssionEntity的id查找MissionEntity实体
     * @param id 文件实体的id
     * @return 任务实体
     */
    public @Nullable MissionEntity findByFileMissionId(int id){
        String jpql = "SELECT f.missionEntity FROM FileMissionEntity f WHERE f.id=:id";
        Query query = getEntityManager().createQuery(jpql);
        query.setParameter("id",id);
        MissionEntity missionEntity;
        try {
            missionEntity = (MissionEntity) query.getSingleResult();
        } catch (NoResultException e) {
            logger.info("未找到实体");
            return null;
        }

        return  missionEntity;
    }

}
