package com.service;

import com.entity.AdministratorEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

public interface AdministratorService {
    /**
     * 增加管理员
     *
     * @param teacherId 教师id
     * @return 是否添加成功到管理员
     */
    boolean addAdministrator(int teacherId);

    /**
     * 删除管理员
     *
     * @param administratorId 管理员id
     * @return 是否成功删除管理员
     */
    boolean deleteAdministrator(int administratorId);

    /**
     * 查询所有的adminstrator
     *
     * @return administrators
     */
    @NotNull Set<AdministratorEntity> findAdminstrators();

    /**
     * 根据教师id查找其是否为管理员
     *
     * @param teacherId 教师id
     * @return 返回找到的对象
     */
    @Nullable AdministratorEntity findAdministrator(int teacherId);


}
