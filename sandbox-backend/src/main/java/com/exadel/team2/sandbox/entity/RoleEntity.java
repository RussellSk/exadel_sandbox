package com.exadel.team2.sandbox.entity;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "ROLE")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "RL_ID")
    private Long rlId;

    @Column(name = "RL_NAME")
    private String rlName;

    @Column(name = "RL_DESCRIPTION")
    private String rlDescription;

    @OneToMany
    @JoinTable(name = "ROLE_PERMISSION", joinColumns = @JoinColumn(name = "RL_ID"),
            inverseJoinColumns = @JoinColumn(name = "PMN_ID"))
    private List<PermissionEntity> permissions = new ArrayList<>();

    @Column(name = "RL_CREATED_AT")
    private LocalDateTime rlCreatedAt;

    @Column(name = "RL_UPDATED_AT")
    private LocalDateTime rlUpdatedAt;
}
