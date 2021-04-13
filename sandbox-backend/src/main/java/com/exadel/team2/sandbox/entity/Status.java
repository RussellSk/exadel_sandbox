package com.exadel.team2.sandbox.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)

@Entity
@Table(name = "STATUS")
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "ST_ID", nullable = false)
    private Long id;

    @Column(name = "ST_NAME", length = 55, nullable = false)
    private String name;

    @Column(name = "ST_DESCRIPTION", nullable = false)
    private String description;

    @CreationTimestamp
    @Column(name = "ST_CREATED_AT")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "ST_UPDATED_AT")
    private LocalDateTime updatedAt;

}

