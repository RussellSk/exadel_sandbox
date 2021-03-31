package com.exadel.team2.sandbox.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

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
    private long id;

    @Column(name = "IFB_FEEDBACK")
    private String feedback;

    @Column(name = "IFB_CREATED_AT")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateOfCreate;

    @Column(name = "IFB_UPDATED_AT")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateOfUpdate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "EMP_ID")
    @Column(name = "EMP_ID")
    private long empId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CN_ID")
    @Column(name = "CN_ID")
    private long cnId;


}
