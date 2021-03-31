package com.exadel.team2.sandbox.entity;


import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "EVENT")
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "EV_ID")
    private Long evId;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH} )
    @JoinColumn(name = "IMG_ID")
    private ImageEntity image; 

//    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH} )
//    @JoinColumn(name = "EMP_ID")
//    private Employee employee;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH} )
    @JoinColumn(name = "EVT_ID")
    private EventTypeEntity eventType;

    @Column(name = "EV_SHORT_DESCRIPTION")
    private String evShortDescription;

    @Column(name = "EV_FULL_DESCRIPTION")
    private String evFullDescription;

    @Column(name = "EV_START_DATE")
    private LocalDateTime evStartDate;

    @Column(name = "EV_DURATION")
    private Duration evDuration;

    @Column(name = "EV_DEADLINE")
    private LocalDateTime evDeadline;

    @Column(name = "EV_LOCATION")
    private String evLocation;

    @Column(name = "EV_CANDIDATE_REQUIREMENTS")
    private String evCandidateRequirements;

    @CreationTimestamp
    @Column(name = "EV_CREATED_AT")
    private LocalDate evCreatedAt;

    @UpdateTimestamp
    @Column(name = "EV_UPDATED_AT")
    private LocalDate evUpdatedAt;

}
