package com.exadel.team2.sandbox.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "EMPLOYEE")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "EMP_ID")
    private Long empId;

    @OneToOne
    @JoinColumn(name = "RL_ID", referencedColumnName = "RL_ID")
    private RoleEntity role;

    @Column(name = "EMP_FIRST_NAME")
    private String empFirstName;

    @Column(name = "EMP_LAST_NAME")
    private String empLastName;

    @Column(name = "EMP_PHONE")
    private String empPhone;

    @Column(name = "EMP_EMAIL")
    private String empEmail;

    @Column(name = "EMP_SKYPE")
    private String empSkype;

    @Column(name = "EMP_LOCATION_COUNTRY")
    private String empLocationCountry;

    @Column(name = "EMP_LOCATION_CITY")
    private String empLocationCity;

    @Column(name = "EMP_TIMEZONE")
    private String empTimezone;

    @Column(name = "EMP_PASSWORD")
    private String empPassword;

    @Column(name = "EMP_CREATED_AT")
    private LocalDateTime empCreatedAt;

    @Column(name = "EMP_UPDATED_AT")
    private LocalDateTime empUpdatedAt;
}