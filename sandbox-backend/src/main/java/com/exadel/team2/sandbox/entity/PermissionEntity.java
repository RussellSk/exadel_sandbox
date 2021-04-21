package com.exadel.team2.sandbox.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "PERMISSION")
@AttributeOverride(name = "id", column =  @Column(name = "PMN_ID"))
@EqualsAndHashCode(callSuper = true)
public class PermissionEntity extends BaseEntity {

    @Column(name = "PMN_NAME")
    private String name;

    @Column(name = "PMN_CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "PMN_UPDATED_AT")
    private LocalDateTime updatedAt;
}
