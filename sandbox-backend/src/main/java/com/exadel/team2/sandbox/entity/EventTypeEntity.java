package com.exadel.team2.sandbox.entity;


import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "EVENT_TYPE")
public class EventTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "EVT_ID")
    private Long id;

    @Column(name = "EVT_NAME")
    private String name;

    @Column(name = "EVT_DESCRIPTION")
    private String description;

    @CreationTimestamp
    @Column(name = "EVT_CREATED_AT")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "EVT_UPDATED_AT")
    private LocalDateTime updatedAt;

}
