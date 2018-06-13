package com.dao;

import com.entity.TeacherEntity;

import javax.persistence.NoResultException;
import javax.persistence.Query;


public class TeacherEntityDao extends GenericDao<TeacherEntity> {
    public TeacherEntity find(String account){
        String jpql ="FROM TeacherEntity t WHERE t.account=:account";
        Query query = getEntityManager().createQuery(jpql);
        query.setParameter("account",account);
        TeacherEntity teacherEntity = null;
        try {
            teacherEntity = (TeacherEntity) query.getSingleResult();
        }catch (NoResultException e){
            //异常还没有写
        }
        return teacherEntity;
    }
}
