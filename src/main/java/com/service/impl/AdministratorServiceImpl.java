package com.service.impl;

import com.dao.AdministratorEntityDao;
import com.entity.AdministratorEntity;
import com.service.AdministratorService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AdministratorServiceImpl implements AdministratorService {
    private final AdministratorEntityDao administratorEntityDao;

    @Autowired
    public AdministratorServiceImpl(AdministratorEntityDao administratorEntityDao) {
        this.administratorEntityDao = administratorEntityDao;
    }

    @Override
    public @NotNull AdministratorEntity addAdministrator(AdministratorEntity administrator) {
        return administratorEntityDao.add(administrator);
    }

    @Override
    public void deleteAdministrator(AdministratorEntity administrator) {
        administratorEntityDao.delete(administrator);
    }

    @Override
    public @NotNull List<AdministratorEntity> findAdministrators() {
        return administratorEntityDao.list();
    }

    @Override
    public @NotNull Optional<AdministratorEntity> findAdministratorByTeacherId(int teacherId) {
        return Optional.ofNullable(administratorEntityDao.findByTeacherId(teacherId));
    }

    @Override
    public @NotNull Optional<AdministratorEntity> findAdministratorById(int administratorId) {
        return Optional.ofNullable(administratorEntityDao.find(administratorId));
    }
}
