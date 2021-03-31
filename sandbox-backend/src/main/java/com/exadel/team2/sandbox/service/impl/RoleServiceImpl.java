package com.exadel.team2.sandbox.service.impl;

import com.exadel.team2.sandbox.dao.RoleDAO;
import com.exadel.team2.sandbox.entity.RoleEntity;
import com.exadel.team2.sandbox.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleDAO roleDAO;

    @Override
    public RoleEntity getById(Long id) {
        return roleDAO.findById(id).orElse(null);
    }

    @Override
    public List<RoleEntity> getAll() {
        return roleDAO.findAll();
    }

    @Override
    public RoleEntity save(RoleEntity roleEntity) {
        return roleDAO.save(roleEntity);
    }

    @Override
    public RoleEntity update(RoleEntity roleEntity) {
        return roleDAO.save(roleEntity);
    }

    @Override
    public void delete(Long id) {
        roleDAO.deleteById(id);
    }
}
