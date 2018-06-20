package com.dao;

import com.entity.ExamEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * ExamEntityDao 类
 * 相对于父类新增根据考试名查找考试实体的方法
 * 相对于父类新增根据考试实体里课程实体的id属性查找考试实体的方法
 * 相对于父类新增根据考试实体里管理员实体的id属性查找这个管理员创建的考试实体的方法
 */
@Repository
public class ExamEntityDao extends GenericDao<ExamEntity> {
    public ExamEntityDao() {
    }

    /**
     * 根据考试名称查找考试实体
     * 考试实体可空 考试名称不可空
     * @param name 考试名称
     * @return 考试实体
     */
    public @Nullable ExamEntity findByName(@NotNull String name){
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
     * 考试实体集合可空 id不可空
     * 增加了 @SuppressWarnings("unchecked") 的声明 可能会有问题
     * @param id 课程的id
     * @return 考试实体集合
     */
    @SuppressWarnings("unchecked")
    public @Nullable List<ExamEntity> findByCourseId(int id){
        String jpql = "SELECT c.examEntities FROM  CourseEntity c WHERE c.id=:id";
        Query query = getEntityManager().createQuery(jpql);
        query.setParameter("id",id);
        List<ExamEntity> examEntities = new ArrayList<>();
        try {
            examEntities = query.getResultList();
        }catch (NoResultException e){
            //采用统一异常处理
        }
        return examEntities;
    }

    /**
     * 根据考试实体里管理员实体的id属性查找这个管理员创建的考试实体
     * 考试实体可空 id不可空
     * 增加了 @SuppressWarnings("unchecked") 的声明 可能会有问题
     * @param id 管理员的id
     * @return 考试实体集合
     */
    @SuppressWarnings("unchecked")
    public @Nullable List<ExamEntity> findByAdministratorId(int id){
        String jpql = "SELECT a.examEntities FROM AdministratorEntity a WHERE a.id=:id";
        Query query = getEntityManager().createQuery(jpql);
        query.setParameter("id",id);
        List<ExamEntity> examEntities = new ArrayList<>();
        try {
            examEntities = query.getResultList();
        }catch (NoResultException e){
            //采用统一异常处理
        }
        return examEntities;
    }
}
