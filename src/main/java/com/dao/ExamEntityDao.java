package com.dao;

import com.entity.ExamEntity;

import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 * ExamEntityDao 类 相对于父类新增根据考试名查找考试实体的方法
 */
public class ExamEntityDao extends GenericDao<ExamEntity> {
    public ExamEntityDao() {
    }

    /**
     * 根据考试名称查找考试实体
     * @param name 考试名称
     * @return 考试实体
     */
    public ExamEntity findByName(String name){
        String jpql = "FROM ExamEntity e WHERE e.name=:name";
        Query query = getEntityManager().createQuery(jpql);
        query.setParameter("name",name);
        ExamEntity examEntity = null;
        try {
            examEntity = (ExamEntity) query.getSingleResult();
        } catch (NoResultException e){
            //采用统一异常处理
        }
        return  examEntity;
    }
}
