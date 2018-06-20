package com.service;

import com.entity.TeacherEntity;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public interface TeacherService {
    /**
     * 根据id查询教师信息
     *
     * @param id 数据库id
     * @return 教师
     */
    @NotNull Optional<TeacherEntity> findTeacher(int id);

    /**
     * 根据账号查询
     *
     * @param account 账号
     * @return 教师
     */
    @NotNull Optional<TeacherEntity> findTeacher(@NotNull String account);

    /**
     * 根据教师姓名查找
     *
     * @param name 教师姓名
     * @return 教师的集合
     */
    @NotNull List<TeacherEntity> findTeachers(@NotNull String name);

    /**
     * 增加教师
     *
     * @param teacher teacher对象
     * @return 成功返回新的teacher实体, 失败返回null
     */
    @NotNull Optional<TeacherEntity> addTeacher(@NotNull TeacherEntity teacher);

    /**
     * 根据id删除教师
     *
     * @param teacher 有效的含有id的教师类
     * @return 是否删除成功
     */
    boolean deleteTeacher(@NotNull TeacherEntity teacher);

    /**
     * 根据新的教师信息修改旧的教师信息
     *
     * @param teacherEntity teacher中需要带有旧的教师信息的id
     * @return 成功返回新的teacher实体
     */
    @NotNull Optional<TeacherEntity> updateTeacher(@NotNull TeacherEntity teacherEntity);

    /**
     * 根据教师id判断其是否是管理员，与administratorService.findAdministrator关联
     *
     * @param teacher 有效的含有教师id的教师类
     * @return 真假
     */
    boolean isAdministrator(@NotNull TeacherEntity teacher);


}
