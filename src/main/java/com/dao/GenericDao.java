package com.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * DAO层的抽象父类泛型 为子类提供重载方法
 * @param <T> 实体类泛型
 */
public abstract class GenericDao<T> {
    @PersistenceContext
    private EntityManager entityManager;
    private Class<T> clazz;

    public GenericDao(Class<T> clazz) {
        this.clazz = clazz;
    }


    @SuppressWarnings({"unchecked","rawtypes"})
    GenericDao(){
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt= (ParameterizedType) t;
        clazz = (Class) pt.getActualTypeArguments()[0];
    }

    /**
     * merge方法
    * @param entity 实体泛型
     */
    public void merge(T entity){
        entityManager.merge(entity);
    }

    /**
     * flush方法
     */
    public void flush(){
        entityManager.flush();
    }

    /**
     * refresh方法
     * @param entity 实体泛型
     */
    public void refresh(T entity){
        entityManager.refresh(entity);
    }

    /**
     * persist方法
     * @param entity 实体泛型
     */
    public void persist(T entity){
        entityManager.persist(entity);
    }

    /**
     * remove方法
     * @param entity 实体泛型
     */
    public void remove(T entity){
        entityManager.remove(entity);
    }

    /**
     * clear方法
     */
    public void clear(){
        entityManager.clear();
    }

    /**
     * detach方法
     * @param entity 实体泛型
     */
    public void detach(T entity){
        entityManager.detach(entity);
    }

    /**
     * 实体管理器
     */
    EntityManager getEntityManager(){
        return entityManager;
    }

    /**
     * 查找实体
     * @param id entity.id
     * @return 找到的类的id //应该是吧
     */
    public T find(int id){
        return entityManager.find(clazz,id);
        //这里提示有一个warning 说(T)返回多余 不知道为什么 然后我删了
        // 原代码是return (T) entityManager.find(clazz,id);
    }

    /**
     * 列出数据库中所有的实体 //应该是
     * @return 所有的实体列
     */
    @SuppressWarnings("unchecked")
    public List<T> list(){
        String jpql ="FROM" +clazz.getName();
        Query query = entityManager.createQuery(jpql);
        return query.getResultList();
    }

    /**
     * 列出 firstResult 到 maxResult 的查询结果
     * @param firstResult 从第1个结果开始
     * @param maxResult 到第Max个结果
     * @return 从第1个到第Max个的实体列
     */
    @SuppressWarnings("unchecked")
    public List<T> list(int firstResult,int maxResult){
        String jpql ="FROM" +clazz.getName();
        Query query =entityManager.createQuery(jpql);
        query.setFirstResult(firstResult).setMaxResults(maxResult);
        return query.getResultList();
    }

    /**
     * 可能有问题 以后还要再改
     * @param entity  实体泛型
     */
    public void add(T entity){
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    /**
     * 可能有问题 以后还要再改
     * @param entity 实体泛型
     */
    public void delete(T entity){
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    /**
     * 可能有问题 以后还要再改
     * @param entity  实体泛型
     */
    public void edit(T entity){
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

}
