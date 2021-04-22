package com.exadel.team2.sandbox.dao;

import com.exadel.team2.sandbox.entity.RoleToUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleToUserDAO extends JpaRepository<RoleToUserEntity, Integer> {

    RoleToUserEntity findByName(String name);
}
