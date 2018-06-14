package com.dao;

import com.entity.AdministratorEntity;

import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 * AdministratorEntityDao 类
 * 相对于父类新增根据管理员实体类里老师实体的id查找管理员的方法
 * 待改进
 */
public class AdministratorEntityDao extends GenericDao<AdministratorEntity> {
    public AdministratorEntityDao() {
    }

    /**
     * 根据管理员实体类里老师实体的id查找管理员
     * 可能以后要添加判断功能 暂时先这样
     * @param id 老师的id
     * @return 管理员实体
     */
    public AdministratorEntity findByTeacherId(String id){
        String jpql = "SELECT t.administratorEntity FROM TeacherEntity t WHERE t.id=:id";
        //String jpql = "SELECT e.classroomEntities FROM ExamEntity e WHERE e.id=:id";
        Query query = getEntityManager().createQuery(jpql);
        query.setParameter("id",id);
        AdministratorEntity administratorEntity = null;
        try {
            administratorEntity = (AdministratorEntity) query.getSingleResult();
        }catch (NoResultException e){
            //采用统一异常处理
        }
        return administratorEntity;
    }

}