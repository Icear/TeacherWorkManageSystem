package com.dao;

import com.entity.TitleEntity;

import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 * TitleEntityDao 类
 * 相对与父类新增根据职称名称查找职称实体方法
 */
public class TitleEntityDao extends GenericDao<TitleEntity> {
    public TitleEntityDao() {
    }
    /**
     * 采用职称名称查找职称实体
     * @param name 职称名称
     * @return 职称实体
     */
    public TitleEntity findByName(String name){
        String jpql = "FROM TitleEntity t WHERE t.name=:name";
        Query query = getEntityManager().createQuery(jpql);
        query.setParameter("name",name);
        TitleEntity titleEntity = null;
        try {
            titleEntity = (TitleEntity) query.getSingleResult();
        } catch (NoResultException e){
            //采用统一异常处理
        }
        return  titleEntity;
    }
}
