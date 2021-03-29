package com.exadel.team2.sandbox.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "STATUS_HISTORY", schema = "sandbox")
public class StatusHistory {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "STH_ID", nullable = false)
    private Long id;

    @Column(name = "STH_CHANGE_AT", length = 500)
    private String changeNote;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "STH_CREATED_AT")
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "STH_UPDATED_AT")
    private Date updatedAt;

}
