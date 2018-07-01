package com.dao;

import com.entity.AdministratorEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 * AdministratorEntityDao 类
 * 相对于父类新增根据管理员实体类里老师实体的id查找管理员的方法
 * 待改进
 */
@Repository
public class AdministratorEntityDao extends GenericDao<AdministratorEntity> {
    public AdministratorEntityDao() {
    }

    private static Logger logger = LogManager.getLogger(AdministratorEntityDao.class);

    /**
     * 根据管理员实体类里老师实体的id查找管理员
     * 返回的管理员实体可空 id不可空
     * 可能以后要添加判断功能 暂时先这样
     * @param id 老师的id
     * @return 管理员实体
     */
    public @Nullable AdministratorEntity findByTeacherId(int id){
        String jpql = "SELECT t.administratorEntity FROM TeacherEntity t WHERE t.id=:id";
        //String jpql = "SELECT e.classroomEntities FROM ExamEntity e WHERE e.id=:id";
        Query query = getEntityManager().createQuery(jpql);
        query.setParameter("id",id);
        AdministratorEntity administratorEntity;
        try {
            administratorEntity = (AdministratorEntity) query.getSingleResult();
        } catch (NoResultException e) {
            logger.info("未找到实体");
            return null;
        }
        return administratorEntity;
    }

}
