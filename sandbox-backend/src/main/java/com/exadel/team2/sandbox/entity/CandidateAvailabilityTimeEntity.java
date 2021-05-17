package com.exadel.team2.sandbox.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "CANDIDATE_AVAILABILITY_TIME")
@AttributeOverride(name= "id", column = @Column(name = "CAT_ID"))
@EqualsAndHashCode(callSuper = true)
public class CandidateAvailabilityTimeEntity extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "CN_ID", referencedColumnName = "CN_ID")
    private CandidateEntity candidate;

    @Column(name = "CAT_DATETIME")
    private LocalDateTime dateTime;

    @Column(name = "CAT_CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "CAT_UPDATED_AT")
    private LocalDateTime updatedAt;

}
