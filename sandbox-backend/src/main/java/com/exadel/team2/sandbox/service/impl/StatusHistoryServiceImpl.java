//package com.exadel.team2.sandbox.service.impl;
//
//import com.exadel.team2.sandbox.dao.StatusHistoryDAO;
//import com.exadel.team2.sandbox.entity.StatusHistory;
//import com.exadel.team2.sandbox.service.StatusHistoryService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Service
//@Transactional
//@RequiredArgsConstructor
//public class StatusHistoryServiceImpl implements StatusHistoryService {
//
//    private final StatusHistoryDAO dao;
//
//    @Override
//    public StatusHistory findById(Long id) {
//        return dao.findById(id).orElse(null);
//    }
//
//    @Override
//    public List<StatusHistory> findAll() {
//        return dao.findAll();
//    }
//
//    @Override
//    public StatusHistory save(StatusHistory entity) {
//        return dao.save(entity);
//    }
//
//    @Override
//    public StatusHistory update(StatusHistory entity) {
//        return dao.save(entity);
//    }
//
//    @Override
//    public void deleteById(Long id) {
//        dao.deleteById(id);
//    }
//}
