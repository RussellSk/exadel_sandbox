package com.exadel.team2.sandbox.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "INTERVIEW_TIME")
public class InterviewTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITM_ID")
    private Long itmId;

    @Column(name = "EV_ID")
    private Long evId;

//    @OneToMany (mappedBy = "interviewTimeEntity", fetch = FetchType.EAGER)
//    private Collection<EventEntity> events;

    @Column(name = "CN_ID", nullable = false)
    private Long cnId;

    @OneToMany(mappedBy = "interviewTimeEntity")
    private Collection<CandidateEntity> candidateEntity;

    @Column(name = "EMP_ID")
    private Long empId;

//    @OneToOne(mappedBy = "interviewTimeEntity")
//    private EmployeeEntity employeeEntity;

    @Column(name = "ITM_BEGIN_DATE")
    private LocalDateTime beginDate;

    @CreationTimestamp
    @Column(name = "ITM_CREATED_AT", nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "ITM_UPDATED_AT")
    private LocalDateTime updatedAt;

}
