package com.exadel.team2.sandbox.entity;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "PERMISSION")
public class PermissionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "PMN_ID")
    private Long pmnId;

    @Column(name = "PMN_NAME")
    private String pmnName;

    @Column(name = "PMN_CREATED_AT")
    private LocalDateTime pmnCreatedAt;

    @Column(name = "PMN_UPDATED_AT")
    private LocalDateTime pmnUpdatedAt;
}
