package com.exadel.team2.sandbox.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
//@Setter
//@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "resume")
public class ResumeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "RSM_ID", nullable = false)
    private Long rsm_id;

//    @JsonBackReference
    @OneToOne(mappedBy = "resumeEntity")
    private CandidateEntity candidateEntity;

    @Column(name = "RSM_PATH")
    private String rsm_path;

    @Column(name = "RSM_LINK")
    private String rsm_link;

    @Column(name = "RSM_NAME")
    private String rsm_name;

    @Column(name = "RSM_EXT", nullable = false)
    private String rsm_ext;

    @Column(name = "RSM_SIZE", nullable = false)
    private int rsm_size;

    @CreationTimestamp
    @Column(name = "RSM_CREATED_AT", nullable = false)
    private LocalDateTime rsm_created_at;
}
