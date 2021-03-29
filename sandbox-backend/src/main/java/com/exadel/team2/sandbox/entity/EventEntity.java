package com.exadel.team2.sandbox.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "EVENT")
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "EV_ID")
    private Long evId;

    @Column(name = "IMG_ID")
    private String imgID;          //FK

    @Column(name = "IMP_ID")
    private String impId;         //FK

    @Column(name = "EVT_ID")
    private String evtId;         //FK

    @Column(name = "EV_SHORT_DESCRIPTION")
    private String evShortDescription;

    @Column(name = "EV_FULL_DESCRIPTION")
    private String evFullDescription;

    @Column(name = "EV_START_DATE")
    @Temporal(TemporalType.DATE)
    private Date evStartDate;         

    @Column(name = "EV_DURATION")
    private String evDuration;          //datetime ЧИ СТРИНГ???????

    @Column(name = "EV_DEADLINE")
    @Temporal(TemporalType.DATE)
    private Date evDeadline;

    @Column(name = "EV_LOCATION")
    private String evLocation;

    @Column(name = "EV_CANDIDATE_REQUIREMENTS")
    private String evCandidateRequirements;

    @Column(name = "EV_CREATED_AT")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date evCreatedAt;

    @Column(name = "EV_UPDATED_AT")
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date evUpdatedAt;

}
