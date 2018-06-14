package com.dao;

import com.entity.ClassroomEntity;

import javax.persistence.NoResultException;
import javax.persistence.Query;


/**
 * ClassroomEntityDao 类 相对于父类新增根据教室信息查找教室实体的方法
 */
public class ClassroomEntityDao extends GenericDao<ClassroomEntity> {

    public ClassroomEntityDao() {
    }
    /**
     * 根据班级信息查找班级实体
     * @param classInformation 班级信息
     * @return 班级实体
     */
    public ClassroomEntity findByClassInformation (String classInformation){
        String jpql ="FROM ClassroomEntity c WHERE c.classInformation=:classInformation";
        Query query = getEntityManager().createQuery(jpql);
        query.setParameter("classInformation",classInformation);
        ClassroomEntity classroomEntity = null;
        try {
            classroomEntity = (ClassroomEntity) query.getSingleResult();
        }catch (NoResultException e){
            //采用统一异常处理
        }
        return classroomEntity;
    }


}
