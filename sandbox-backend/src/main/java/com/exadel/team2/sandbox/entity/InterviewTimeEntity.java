package com.exadel.team2.sandbox.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ITM_ID", nullable = false)
    private Long itm_id;

    @Column(name = "EV_ID")
    private Long ev_id;

//    @OneToMany (mappedBy = "interviewTime", fetch = FetchType.EAGER)
//    private Collection<EventEntity> events;

    @Column(name = "CN_ID", nullable = false)
    private Long cn_id;

//    @JsonBackReference
    @OneToMany(mappedBy = "interviewTimeEntity", fetch = FetchType.LAZY)
    private Collection<CandidateEntity> candidateEntity;

    @Column(name = "EMP_ID")
    private Long emp_id;

//    @OneToOne(mappedBy = "employee")
//    private EmployeeEntity employeeEntity;

    @Column(name = "ITM_BEGIN_DATE")
    private LocalDateTime itm_begin_date;

    @CreationTimestamp
    @Column(name = "ITM_CREATED_AT", nullable = false)
    private LocalDateTime itm_created_at;

    @UpdateTimestamp
    @Column(name = "ITM_UPDATED_AT")
    private LocalDateTime itm_updated_at;
}
