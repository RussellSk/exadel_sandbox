package com.exadel.team2.sandbox.service;

import com.exadel.team2.sandbox.entity.Status;

import java.util.List;

public interface StatusService {

    Status findById(Long id);

    List<Status> findAll();

    Status save(Status entity);

    Status update(Status entity);

    void deleteById(Long id);
}
