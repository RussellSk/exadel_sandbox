//package com.exadel.team2.sandbox.entity;
//
//
//import lombok.Data;
//import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.GenericGenerator;
//import org.hibernate.annotations.UpdateTimestamp;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//
//@Data
//
//@Entity
//@Table(name = "STATUS_HISTORY")
//public class StatusHistory {
//
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
//    @GenericGenerator(name = "native", strategy = "native")
//    @Column(name = "STH_ID", nullable = false)
//    private Long id;
//
//    @Column(name = "STH_CHANGE_AT", length = 500)
//    private String changeNote;
//
//    @CreationTimestamp
//    @Column(name = "STH_CREATED_AT",nullable = false)
//    private LocalDateTime createdAt;
//
//    @UpdateTimestamp
//    @Column(name = "STH_UPDATED_AT")
//    private LocalDateTime updatedAt;
//
//}
