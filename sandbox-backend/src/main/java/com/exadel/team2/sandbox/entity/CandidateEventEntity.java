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

    @ManyToMany
    @JoinTable(// TODO: 30.03.2021 will read and replace
            name="CN_ID",
            joinColumns=@JoinColumn(name="CNEV_ID", referencedColumnName="CNEV_ID"),
            inverseJoinColumns=@JoinColumn(name="CN_ID", referencedColumnName="CN_ID"))
    @Column(name = "CN_ID")
    private int cnId;

    @ManyToMany
    @JoinTable(
            // TODO: 30.03.2021 will add code (maybe @ManyToMany (mappedBy="event")
    )
    @Column(name = "EV_ID")
    private int evId;

    @Column(name = "CNV_CREATED_AT")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;


}
