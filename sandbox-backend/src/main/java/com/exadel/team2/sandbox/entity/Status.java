package com.exadel.team2.sandbox.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data

@Entity
@Table(name = "STATUS")
@AttributeOverride(name = "id", column = @Column(name = "ST_ID"))
@EqualsAndHashCode(callSuper = true)
public class Status extends BaseEntity {

    @Column(name = "ST_NAME", length = 55, nullable = false)
    private String name;

    @Column(name = "ST_DESCRIPTION", nullable = false)
    private String description;

    @CreationTimestamp
    @Column(name = "ST_CREATED_AT", nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "ST_UPDATED_AT")
    private LocalDateTime updatedAt;

}

