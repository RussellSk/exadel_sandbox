package com.exadel.team2.sandbox.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "role_table")
@Data
public class RoleToUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;
}