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
@Table(name = "EVENT_TYPE")
public class EventTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "EVT_ID")
    private Long evtId;

    @Column(name = "EVT_NAME")
    private String evtName;

    @Column(name = "EVT_DESCRIPTION")
    private String evtDescription;

    @Column(name = "EVT_CREATED_AT")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date evtCreatedAt;

    @Column(name = "EVT_UPDATED_AT")
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date evtUpdatedAt;
}
