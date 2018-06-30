package com.service.impl;

import com.dao.TitleEntityDao;
import com.entity.TitleEntity;
import com.service.TitleService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TitleServiceImpl implements TitleService {
    private final TitleEntityDao titleEntityDao;

    @Autowired
    public TitleServiceImpl(TitleEntityDao titleEntityDao) {
        this.titleEntityDao = titleEntityDao;
    }

    @Override
    public @NotNull TitleEntity addTitle(@NotNull TitleEntity title) {
        return titleEntityDao.add(title);
    }

    @Override
    public void deleteTitle(@NotNull TitleEntity title) {
        titleEntityDao.delete(title);
    }

    @Override
    public @NotNull Optional<TitleEntity> updateTitle(@NotNull TitleEntity title) {
        return Optional.ofNullable(titleEntityDao.edit(title));
    }

    @Override
    public @NotNull List<TitleEntity> findTitles() {
        return titleEntityDao.list();
    }

    @Override
    public @NotNull Optional<TitleEntity> findTitleByName(@NotNull String titleName) {
        return Optional.ofNullable(titleEntityDao.findByName(titleName));
    }

    @Override
    public @NotNull Optional<TitleEntity> findById(@NotNull Integer id) {
        return Optional.ofNullable(titleEntityDao.find(id));
    }
}
