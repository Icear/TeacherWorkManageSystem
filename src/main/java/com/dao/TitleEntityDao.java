package com.dao;

import com.entity.TitleEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

/**
 * TitleEntityDao 类
 * 相对与父类新增根据职称名称查找职称实体方法
 */
@Repository
public class TitleEntityDao extends GenericDao<TitleEntity> {
    public TitleEntityDao() {
    }
    /**
     * 采用职称名称查找职称实体
     * 职称实体可空 职称名称不可空
     * @param name 职称名称
     * @return 职称实体
     */
    public @Nullable TitleEntity findByName(@NotNull String name){
        String jpql = "FROM TitleEntity t WHERE t.name=:name";
        Query query = getEntityManager().createQuery(jpql);
        query.setParameter("name",name);
        TitleEntity titleEntity;
        titleEntity = (TitleEntity) query.getSingleResult();
        return  titleEntity;
    }
}
