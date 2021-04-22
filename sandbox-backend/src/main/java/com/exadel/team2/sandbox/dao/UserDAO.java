package com.exadel.team2.sandbox.dao;

import com.exadel.team2.sandbox.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO  extends JpaRepository<UserEntity, Integer> {

        UserEntity findByLogin(String login);
}