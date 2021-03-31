package com.exadel.team2.sandbox.service.impl;

import com.exadel.team2.sandbox.dao.PermissionDAO;
import com.exadel.team2.sandbox.entity.PermissionEntity;
import com.exadel.team2.sandbox.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private final PermissionDAO permissionDAO;

    @Override
    public PermissionEntity getById(Long id) {
        return permissionDAO.findById(id).orElse(null);
    }

    @Override
    public List<PermissionEntity> getAll() {
        return permissionDAO.findAll();
    }

    @Override
    public PermissionEntity save(PermissionEntity permissionEntity) {
        return permissionDAO.save(permissionEntity);
    }

    @Override
    public PermissionEntity update(PermissionEntity permissionEntity) {
        return permissionDAO.save(permissionEntity);
    }

    @Override
    public void delete(Long id) {
        permissionDAO.deleteById(id);
    }
}
