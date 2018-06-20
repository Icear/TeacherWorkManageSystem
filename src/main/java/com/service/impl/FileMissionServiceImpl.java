package com.service.impl;

import com.dao.FileMissionEntityDao;
import com.entity.FileMissionEntity;
import com.service.FileMissionService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FileMissionServiceImpl implements FileMissionService {
    private final FileMissionEntityDao fileMissionEntityDao;

    @Autowired
    public FileMissionServiceImpl(FileMissionEntityDao fileMissionEntityDao) {
        this.fileMissionEntityDao = fileMissionEntityDao;
    }

    @Override
    public @NotNull FileMissionEntity addFileMission(@NotNull FileMissionEntity fileMission) {
        return fileMissionEntityDao.add(fileMission);
    }

    @Override
    public @NotNull Optional<FileMissionEntity> updateFileMission(@NotNull FileMissionEntity fileMission) {
        return Optional.ofNullable(fileMissionEntityDao.edit(fileMission));
    }

    @Override
    public void deleteFileMission(@NotNull FileMissionEntity fileMission) {
        fileMissionEntityDao.delete(fileMission);
    }

    @Override
    public @NotNull Optional<FileMissionEntity> findFileMissionByFileMissionId(int fileMissionId) {
        return Optional.ofNullable(fileMissionEntityDao.find(fileMissionId));
    }

    @Override
    public @NotNull List<FileMissionEntity> findAllFileMissions() {
        return fileMissionEntityDao.list();
    }
}
