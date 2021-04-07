package com.exadel.team2.sandbox.entity;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

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

    @OneToMany(mappedBy = "employeeEntity", fetch = FetchType.EAGER)
    private Collection<EventEntity> eventEntities;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "EMP_ID")
//    private InterviewTimeEntity interviewTimeEntity;

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

    @Column(name = "EMP_CREATED_AT")
    private LocalDateTime empCreatedAt;

    @Column(name = "EMP_UPDATED_AT")
    private LocalDateTime empUpdatedAt;
}
