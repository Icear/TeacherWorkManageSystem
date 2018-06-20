package com.dao;

import com.entity.TeacherEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;


/**
 * TeacherEntityDao 类
 * 相对于父类新增根据姓名查找老师实体的方法
 * 相对与父类新增根据账号查找老师实体的方法
 */
@Repository
public class TeacherEntityDao extends GenericDao<TeacherEntity> {

    public TeacherEntityDao() {
    }

//    /**
//     * 重载的find方法 然而不需要重载find方法 只需要调用父类的就行了
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
     * 老师实体可空 账号不可空
     * @param account 账号
     * @return 查找到的老师实体
     */
    public @Nullable TeacherEntity findByAccount(@NotNull String account){
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
     * 老师实体不可空 姓名不可空
     * @param name 姓名
     * @return 查找到的老师实体
     */
    @SuppressWarnings("unchecked")
    public @NotNull List<TeacherEntity> findByName(@NotNull String name){
        String jpql = "FROM TeacherEntity t WHERE t.name=:name";
        Query query = getEntityManager().createQuery(jpql);
        query.setParameter("name",name);
        List<TeacherEntity> teacherEntities = new ArrayList<>();
        try {
            teacherEntities = query.getResultList();
        } catch (NoResultException e){
            //同上 暂时没写 应该采用统一异常处理？
        }
        return  teacherEntities;
    }


}
