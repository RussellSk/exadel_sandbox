package com.exadel.team2.sandbox.service.impl;

import com.exadel.team2.sandbox.dao.ResumeDAO;
import com.exadel.team2.sandbox.entity.ResumeEntity;
import com.exadel.team2.sandbox.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ResumeServiceImpl implements ResumeService {

    private final ResumeDAO resumeDAO;

    @Override
    public ResumeEntity getById(Long id) {
        return resumeDAO.findById(id).orElse(null);
    }

    @Override
    public List<ResumeEntity> getAll() {
        return resumeDAO.findAll();
    }

    @Override
    public ResumeEntity save(ResumeEntity resumeEntity) {
        return resumeDAO.save(resumeEntity);
    }

    @Override
    public ResumeEntity update(ResumeEntity resumeEntity) {
        return resumeDAO.save(resumeEntity);
    }

    @Override
    public void delete(Long id) {
        resumeDAO.deleteById(id);
    }
}
