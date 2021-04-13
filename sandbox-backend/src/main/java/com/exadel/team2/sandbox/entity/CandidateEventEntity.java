package com.exadel.team2.sandbox.entity;


import liquibase.pro.packaged.T;
import lombok.*;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "CANDIDATE_EVENT")
public class CandidateEventEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "CNEV_ID")
    private long id;

    @OneToMany
    @JoinTable(name = "CANDIDATE_EVENT", joinColumns = @JoinColumn(name = "EV_ID"),
            inverseJoinColumns = @JoinColumn(name = "CN_ID"))
    private List<CandidateEntity> candidates= new ArrayList();

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "EV_ID")
    private EventEntity event;

    @Column(name = "CNV_CREATED_AT")
    @CreationTimestamp
    private LocalDateTime createdAt;
}
