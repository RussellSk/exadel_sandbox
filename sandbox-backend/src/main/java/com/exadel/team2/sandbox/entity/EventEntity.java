package com.exadel.team2.sandbox.entity;


import com.exadel.team2.sandbox.entity.enums.EnglishLevel;
import com.exadel.team2.sandbox.entity.enums.EventTab;
import com.exadel.team2.sandbox.entity.enums.Format;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "EVENT")
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "EV_ID")
    private Long id;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH} )
    @JoinColumn(name = "IMG_ID", referencedColumnName = "IMG_ID")
    private ImageEntity image;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH} )
    @JoinColumn(name = "EMP_ID")
    private EmployeeEntity employee;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH} )
    @JoinColumn(name = "EV_CREATOR", referencedColumnName = "EMP_ID")
    private EmployeeEntity creatorEvent;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH } )
    @JoinColumn(name = "EVT_ID", referencedColumnName = "EVT_ID")
    private EventTypeEntity eventType;

    @Enumerated(EnumType.STRING)
    @Column(name = "EV_TAB")
    private EventTab eventTab;

    @Enumerated(EnumType.STRING)
    @Column(name = "EV_ENGLISH_LEVEL")
    private EnglishLevel englishLevel;

    @Column(name = "EV_START_DATE")
    private LocalDate startDate;

    @Column(name = "EV_DURATION")
    private String duration;

    @Column(name = "EV_DEADLINE")
    private LocalDate deadline;

    @Column(name = "EV_DATE_OF_END_ACCEPT")
    private LocalDate dateOfEndAccept;

    @Enumerated(EnumType.STRING)
    @Column(name = "EV_FORMAT")
    private Format format;

    @Column(name = "EV_COUNTRY")
    private String country;

    @Column(name = "EV_CITY")
    private String city;

    @Column(name = "EV_TECHNOLOGIES")
    private String technologies;

    @CreationTimestamp
    @Column(name = "EV_CREATED_AT")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "EV_UPDATED_AT")
    private LocalDateTime updatedAt;

}