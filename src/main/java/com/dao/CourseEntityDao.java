package com.dao;

import com.entity.CourseEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;


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
     * @param courseName 课程名
     * @return 课程实体
     */
    public CourseEntity findByCourseName(String courseName){
        String jpql ="FROM CourseEntity c WHERE c.courseName=:courseName";
        Query query = getEntityManager().createQuery(jpql);
        query.setParameter("courseName",courseName);
        CourseEntity courseEntity = null;
        try {
            courseEntity = (CourseEntity) query.getSingleResult();
        }catch (NoResultException e){
            //采用统一异常处理
        }
        return courseEntity;
    }


}
