package com.exadel.team2.sandbox.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "ROLE")
@AttributeOverride(name = "id", column = @Column(name = "RL_ID"))
@EqualsAndHashCode(callSuper = true)
public class RoleEntity extends BaseEntity {

    @Column(name = "RL_NAME")
    private String name;

    @Column(name = "RL_DESCRIPTION")
    private String description;

    @OneToMany
    @JoinTable(name = "ROLE_PERMISSION", joinColumns = @JoinColumn(name = "RL_ID"),
            inverseJoinColumns = @JoinColumn(name = "PMN_ID"))
    private List<PermissionEntity> permissions = new ArrayList<>();

    @Column(name = "RL_CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "RL_UPDATED_AT")
    private LocalDateTime updatedAt;
}
