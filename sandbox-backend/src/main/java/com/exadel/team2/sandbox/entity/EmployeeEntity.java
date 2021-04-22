package com.exadel.team2.sandbox.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "EMPLOYEE")
@AttributeOverride(name = "id", column = @Column(name = "EMP_ID"))
@EqualsAndHashCode(callSuper = true)
public class EmployeeEntity extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "RL_ID", referencedColumnName = "RL_ID")
    private RoleEntity role;

    @Column(name = "EMP_FIRST_NAME")
    private String firstName;

    @Column(name = "EMP_LAST_NAME")
    private String lastName;

    @Column(name = "EMP_PHONE")
    private String phone;

    @Column(name = "EMP_EMAIL")
    private String email;

    @Column(name = "EMP_SKYPE")
    private String skype;

    @Column(name = "EMP_LOCATION_COUNTRY")
    private String locationCountry;

    @Column(name = "EMP_LOCATION_CITY")
    private String locationCity;

    @Column(name = "EMP_TIMEZONE")
    private String timezone;

    @Column(name = "EMP_PASSWORD")
    private String password;

    @Column(name = "EMP_CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "EMP_UPDATED_AT")
    private LocalDateTime updatedAt;
}