package com.exadel.team2.sandbox.service;

import com.exadel.team2.sandbox.entity.ResumeEntity;

import java.util.List;

public interface ResumeService {

    ResumeEntity getById(Long id);

    List<ResumeEntity> getAll();

    ResumeEntity save(ResumeEntity resumeEntity);

    ResumeEntity update(ResumeEntity resumeEntity);

    void delete(Long id);
}
