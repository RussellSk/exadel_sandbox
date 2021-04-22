package com.exadel.team2.sandbox.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_table")
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String login;

    @Column
    private String password;

    @OneToOne
    @JoinColumn(name = "role_id")
    private RoleToUserEntity roleToUserEntity;

}