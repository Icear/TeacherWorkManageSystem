package com.dao;

import com.entity.TeacherEntity;

import javax.persistence.NoResultException;
import javax.persistence.Query;


/**
 * TeacherEntityDao 类 重载find方法 相对于父类新增根据姓名查找老师实体的方法
 */
public class TeacherEntityDao extends GenericDao<TeacherEntity> {


//    /**
//     * 重载的find方法 不知道需不需要
//     * @param id entity.id
//     * @return
//     */
//    public TeacherEntity find(int id){
//        String jpql ="FROM TeacherEntity t WHERE t.id=:id";
//        Query query = getEntityManager().createQuery(jpql);
//        query.setParameter("id",id);
//        TeacherEntity teacherEntity = null;
//        try {
//            teacherEntity = (TeacherEntity) query.getSingleResult();
//        }catch (NoResultException e){
//            //异常还没有写
//        }
//        return teacherEntity;
//    }

    /**
     * 根据账号查找老师
     * 如果需要重载上方注释的find方法，则修改此类为 findByAccount
     * @param account 账号
     * @return 查找到的老师实体
     */
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

    /**
     * 根据姓名查找老师实体
     * @param name 姓名
     * @return 查找到的老师实体
     */
    public TeacherEntity findByName(String name){
        String jpql = "FROM TeacherEntity t WHERE t.name=:name";
        Query query = getEntityManager().createQuery(jpql);
        query.setParameter("name",name);
        TeacherEntity teacherEntity = null;
        try {
            teacherEntity = (TeacherEntity) query.getSingleResult();
        } catch (NoResultException e){
            //同上 暂时没写 应该采用统一异常处理？
        }
        return  teacherEntity;
    }




}
