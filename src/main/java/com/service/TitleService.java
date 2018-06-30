package com.service;

import com.entity.TitleEntity;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public interface TitleService {
    /**
     * 添加职称信息
     *
     * @param title 职称对象
     * @return 返回对象
     */
    @NotNull TitleEntity addTitle(@NotNull TitleEntity title);

    /**
     * 删除职称
     *
     * @param title title类
     */
    void deleteTitle(@NotNull TitleEntity title);

    /**
     * 更新职称
     *
     * @param title title对象
     * @return 新的title
     */
    @NotNull Optional<TitleEntity> updateTitle(@NotNull TitleEntity title);

    /**
     * 查找所有title
     *
     * @return title对象集合
     */
    @NotNull List<TitleEntity> findTitles();

    /**
     * 根据职称名称查找职称实体
     *
     * @param titleName 职称名称
     * @return 实体
     */
    @NotNull Optional<TitleEntity> findTitleByName(@NotNull String titleName);

    @NotNull Optional<TitleEntity> findById(@NotNull Integer id);
}
