package com.dao;

import com.entity.FileMissionEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;
/**
 * FileMssionEntityDao 类
 * 相对于父类新增根据文件任务名称查找文件任务实体的方法
 */
@Repository
public class FileMissionEntityDao extends GenericDao<FileMissionEntity> {

    private static Logger logger = LogManager.getLogger(FileMissionEntityDao.class);

    public FileMissionEntityDao() {
    }

    /**
     * 采用文件任务名称查找文件任务实体
     * 文件任务实体可空 文件任务名称不可空
     * @param name 文件任务名称
     * @return 文件任务实体
     */
    public @Nullable FileMissionEntity findByName(@NotNull String name){
        String jpql = "FROM FileMissionEntity f WHERE f.name=:name";
        Query query = getEntityManager().createQuery(jpql);
        query.setParameter("name",name);
        FileMissionEntity fileMissionEntity;
        try {
            fileMissionEntity = (FileMissionEntity) query.getSingleResult();
        } catch (NoResultException e) {
            logger.info("未找到实体");
            return null;
        }

        return  fileMissionEntity;
    }
}
