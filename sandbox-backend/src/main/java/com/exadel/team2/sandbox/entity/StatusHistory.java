package com.exadel.team2.sandbox.entity;


import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data

@Entity
@Table(name = "STATUS_HISTORY")
public class StatusHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "STH_ID", nullable = false)
    private Long id;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "ST_ID", nullable = false)
    private Status status;

//    @JoinColumn(name = "CN_ID",
//            nullable = false)
//    private Candidate candidate;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "EMP_ID", nullable = false)
    private EmployeeEntity employee;

    @Column(name = "STH_CHANGE_NOTE", length = 500)
    private String changeNote;

    @CreationTimestamp
    @Column(name = "STH_CREATED_AT", nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "STH_UPDATED_AT")
    private LocalDateTime updatedAt;

}
