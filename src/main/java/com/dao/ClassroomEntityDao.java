package com.dao;

import com.entity.ClassroomEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Repository;

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
     * 返回的教室实体可空 教室信息不可空
     * @param classInformation 教室信息
     * @return 班级实体
     */
    public @Nullable ClassroomEntity findByClassInformation (@NotNull String classInformation){
        String jpql ="FROM ClassroomEntity c WHERE c.classInformation=:classInformation";
        Query query = getEntityManager().createQuery(jpql);
        query.setParameter("classInformation",classInformation);
        ClassroomEntity classroomEntity;
        classroomEntity = (ClassroomEntity) query.getSingleResult();
        return classroomEntity;
    }

    //暂时只是个例子
    /**
     * 根据教室实体里考试实体的id的属性查找教室实体
     * 教室实体不可空 id不可空
     * //应该是没错的 可能需要改进！
     * //以改进 应该不需要改了
     * 增加了 @SuppressWarnings("unchecked") 的声明 可能会有问题
     * @param id 考试id
     * @return 教室实体集合
     */
    @SuppressWarnings("unchecked")
    public @NotNull List<ClassroomEntity> findByExamEntity (int id){
        //String jpql = "FROM ClassroomEntity c WHERE c.examEntity.id=:id";
        String jpql = "SELECT e.classroomEntities FROM ExamEntity e WHERE e.id=:id";
        Query query = getEntityManager().createQuery(jpql);
        query.setParameter("id",id);
        List<ClassroomEntity> classroomEntities;
        classroomEntities = query.getResultList();
        return classroomEntities;
    }

}
