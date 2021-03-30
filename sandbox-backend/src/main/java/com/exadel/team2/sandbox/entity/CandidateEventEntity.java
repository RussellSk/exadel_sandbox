package com.exadel.team2.sandbox.entity;

import liquibase.pro.packaged.T;
import lombok.*;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;


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
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CN_ID")
    @Column(name = "CN_ID")
    private int cnId;

    @OneToMany
    @Column(name = "EV_ID")
    private int evId;

    @Column(name = "CNV_CREATED_AT")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;


}
