package com.exadel.team2.sandbox.entity;


import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "EVENT_TYPE")
public class EventTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "EVT_ID")
    private Long evtId;

    @OneToOne(mappedBy = "eventType", cascade = {CascadeType.PERSIST, CascadeType.REFRESH} )
    private EventEntity event;

    @Column(name = "EVT_NAME")
    private String evtName;

    @Column(name = "EVT_DESCRIPTION")
    private String evtDescription;

    @CreationTimestamp
    @Column(name = "EVT_CREATED_AT")
    private LocalDate evtCreatedAt;

    @UpdateTimestamp
    @Column(name = "EVT_UPDATED_AT")
    private LocalDate evtUpdatedAt;


}
