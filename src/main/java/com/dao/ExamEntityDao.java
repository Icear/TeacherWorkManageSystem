package com.dao;

import com.entity.ExamEntity;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

/**
 * ExamEntityDao 类
 * 相对于父类新增根据考试名查找考试实体的方法
 * 相对于父类新增根据考试实体里课程实体的id属性查找考试实体的方法
 * 相对于父类新增根据考试实体里管理员实体的id属性查找这个管理员创建的考试实体的方法
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

    /**
     * 根据考试实体里课程实体的id属性查找考试实体
     * 增加了 @SuppressWarnings("unchecked") 的声明 可能会有问题
     * @param id 课程的id
     * @return 考试实体集合
     */
    @SuppressWarnings("unchecked")
    public List<ExamEntity> findByCourseId(String id){
        String jpql = "SELECT c.examEntities FROM  CourseEntity c WHERE c.id=:id";
        Query query = getEntityManager().createQuery(jpql);
        query.setParameter("id",id);
        List<ExamEntity> examEntities = null;
        try {
            examEntities = query.getResultList();
        }catch (NoResultException e){
            //采用统一异常处理
        }
        return examEntities;
    }

    /**
     * 根据考试实体里管理员实体的id属性查找这个管理员创建的考试实体
     * 增加了 @SuppressWarnings("unchecked") 的声明 可能会有问题
     * @param id 管理员的id
     * @return 考试实体集合
     */
    @SuppressWarnings("unchecked")
    public List<ExamEntity> findByAdministratorId(String id){
        String jpql = "SELECT a.examEntities FROM AdministratorEntity a WHERE a.id=:id";
        Query query = getEntityManager().createQuery(jpql);
        query.setParameter("id",id);
        List<ExamEntity> examEntities = null;
        try {
            examEntities = query.getResultList();
        }catch (NoResultException e){
            //采用统一异常处理
        }
        return examEntities;
    }
}
