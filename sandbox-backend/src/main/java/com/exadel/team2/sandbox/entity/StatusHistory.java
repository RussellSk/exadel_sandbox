package com.exadel.team2.sandbox.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data

@Entity
@Table(name = "STATUS_HISTORY")
@AttributeOverride(name = "id", column = @Column(name = "STH_ID"))
@EqualsAndHashCode(callSuper = true)
public class StatusHistory extends BaseEntity {


    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "ST_ID", nullable = false)
    private Status status;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "CN_ID", nullable = false)
    private CandidateEntity candidate;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "EMP_ID", nullable = false)
    private EmployeeEntity employee;

    @Column(name = "STH_CHANGE_NOTE", length = 500)
    private String changeNote;

    @CreationTimestamp
    @Column(name = "STH_CREATED_AT")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "STH_UPDATED_AT")
    private LocalDateTime updatedAt;

}
