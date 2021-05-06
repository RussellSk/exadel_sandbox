package com.exadel.team2.sandbox.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "INTERVIEW_FEEDBACK")
public class InterviewFeedbackEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "IFB_ID")
    private Long id;

    @Column(name = "IFB_FEEDBACK")
    private String feedback;

    @Column(name = "IFB_CREATED_AT", nullable = false)
    @CreationTimestamp
    private LocalDateTime dateOfCreate;

    @Column(name = "IFB_UPDATED_AT")
    @UpdateTimestamp
    private LocalDateTime dateOfUpdate;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "EMP_ID")
    private EmployeeEntity employee ;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "CN_ID")
    private CandidateEntity candidate;
}
