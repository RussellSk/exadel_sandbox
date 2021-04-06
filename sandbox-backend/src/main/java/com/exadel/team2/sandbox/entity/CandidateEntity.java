package com.exadel.team2.sandbox.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
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
@Table(name = "CANDIDATE")
public class CandidateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CN_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(name = "CN_ID", insertable = false, updatable = false)
    private InterviewTimeEntity interviewTimeEntity;

//    @OneToMany(mappedBy = "candidateEntity", fetch = FetchType.EAGER)
//    private Collection<EventEntity> eventEntities;

    @Column(name = "RSM_ID", nullable = false)
    private Long rsmId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "RSM_ID", insertable = false, updatable = false)
    private ResumeEntity resumeEntity;

    @Column(name = "CN_FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "CN_LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "CN_PHONE", nullable = false)
    private String phone;

    @Column(name = "CN_EMAIL", nullable = false)
    private String email;

    @Column(name = "CN_SKYPE")
    private String skype;

    @Column(name = "CN_ENGLISH_LEVEL", nullable = false)
    private String englishLevel;

    @Column(name = "CN_EXPERTISE")
    private String expertise;

    @Column(name = "CN_EXPERIENCE")
    private String experience;

    @Column(name = "CN_EDUCATION")
    private String education;

    @Column(name = "CN_LOCATION")
    private String location;

    @CreationTimestamp
    @Column(name = "CN_CREATED_AT")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "CN_UPDATED_AT", nullable = false)
    private LocalDateTime updatedAt;
}
