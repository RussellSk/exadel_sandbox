//package com.exadel.team2.sandbox.service.impl;
//
//import com.exadel.team2.sandbox.dao.StatusDAO;
//import com.exadel.team2.sandbox.entity.Status;
//import com.exadel.team2.sandbox.service.StatusService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Service
//@Transactional
//@RequiredArgsConstructor
//public class StatusServiceImpl implements StatusService {
//    private final StatusDAO dao;
//
//    @Override
//    public Status findById(Long id) {
//        return dao.findById(id).orElse(null);
//    }
//
//    @Override
//    public List<Status> findAll() {
//        return dao.findAll();
//    }
//
//    @Override
//    public Status save(Status entity) {
//        return dao.save(entity);
//    }
//
//    @Override
//    public Status update(Status entity) {
//        return dao.save(entity);
//    }
//
//    @Override
//    public void deleteById(Long id) {
//        dao.deleteById(id);
//    }
//}
