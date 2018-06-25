package com.dao;

import com.entity.CourseEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;


/**
 * CourseEntityDao 类
 * 相对于父类新增根据课程名查找课程实体的方法
 */
@Repository
public class CourseEntityDao extends GenericDao<CourseEntity> {

    public CourseEntityDao() {
    }
    /**
     * 根据课程名查找课程实体
     * 课程实体不可空 课程名不可空
     * @param courseName 课程名
     * @return 课程实体
     */
    @SuppressWarnings("unchecked")
    public @NotNull List<CourseEntity> findByCourseName(@NotNull String courseName){
        String jpql ="FROM CourseEntity c WHERE c.courseName=:courseName";
        Query query = getEntityManager().createQuery(jpql);
        query.setParameter("courseName",courseName);
        List<CourseEntity> courseEntities;
        courseEntities = query.getResultList();
        return courseEntities;
    }


}
