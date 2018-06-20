package com.dao;

import com.entity.ClassroomEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;


/**
 * ClassroomEntityDao 类
 * 相对于父类新增根据教室信息查找教室实体的方法
 * 相对与父类新增根据教室实体里考试实体的id的属性查找教室实体的方法
 */
@Repository
public class ClassroomEntityDao extends GenericDao<ClassroomEntity> {

    public ClassroomEntityDao() {
    }
    /**
     * 根据教室信息查找班级实体
     * @param classInformation 教室信息
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

    //暂时只是个例子
    /**
     * 根据教室实体里考试实体的id的属性查找教室实体
     * 应该是没错的 可能需要改进！
     * 增加了 @SuppressWarnings("unchecked") 的声明 可能会有问题
     * @param id 考试id
     * @return 教室实体集合
     */
    @SuppressWarnings("unchecked")
    public List<ClassroomEntity> findByExamEntity (String id){
        //String jpql = "FROM ClassroomEntity c WHERE c.examEntity.id=:id";
        String jpql = "SELECT e.classroomEntities FROM ExamEntity e WHERE e.id=:id";
        Query query = getEntityManager().createQuery(jpql);
        query.setParameter("id",id);
        List<ClassroomEntity> classroomEntities = null;
        try{
            classroomEntities =  query.getResultList();
        }catch (NoResultException e){
            //采用统一异常处理
        }
        return classroomEntities;
    }

}
