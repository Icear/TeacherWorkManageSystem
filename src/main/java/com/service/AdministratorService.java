package com.service;

import com.entity.AdministratorEntity;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public interface AdministratorService {
    /**
     * 增加管理员
     *
     * @param administrator 需要设置有效的含有教师id的教师类
     * @return 成功返回新的管理员实体，失败则null
     */
    @NotNull Optional<AdministratorEntity> addAdministrator(AdministratorEntity administrator);

    /**
     * 删除管理员
     *
     * @param administrator 有效的含有id的管理员
     * @return 是否成功删除管理员
     */
    boolean deleteAdministrator(AdministratorEntity administrator);

    /**
     * 查询所有的adminstrator
     *
     * @return administrators
     */
    @NotNull List<AdministratorEntity> findAdminstrators();

//    /**
//     * 根据教师id查找其是否为管理员
//     *
//     * @param teacherId 教师id
//     * @return 返回找到的对象
//     */
//    @Nullable AdministratorEntity findAdministrator(int teacherId);


}
