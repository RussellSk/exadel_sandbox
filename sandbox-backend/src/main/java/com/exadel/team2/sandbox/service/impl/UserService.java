package com.exadel.team2.sandbox.service.impl;

import com.exadel.team2.sandbox.dao.RoleToUserDAO;
import com.exadel.team2.sandbox.dao.UserDAO;
import com.exadel.team2.sandbox.entity.RoleToUserEntity;
import com.exadel.team2.sandbox.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService  {

    private final UserDAO userDAO;
    private final RoleToUserDAO roleToUserDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserEntity saveUser(UserEntity userEntity) {
        RoleToUserEntity roleToUserEntity = roleToUserDAO.findByName("ROLE_ADMIN");
        userEntity.setRoleToUserEntity(roleToUserEntity);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return userDAO.save(userEntity);
    }

    public UserEntity findByLogin(String login) {
        return userDAO.findByLogin(login);
    }

    public UserEntity findByLoginAndPassword(String login, String password) {
        UserEntity userEntity = findByLogin(login);
        if (userEntity != null) {
            if (passwordEncoder.matches(password, userEntity.getPassword())) {
                return userEntity;
            }
        }
        return null;
    }
}

