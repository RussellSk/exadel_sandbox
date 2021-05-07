package com.exadel.team2.sandbox.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "EMPLOYEE_AVAILABILITY_TIME")
@AttributeOverride(name = "id", column = @Column(name = "EVAT_ID"))
@EqualsAndHashCode(callSuper = true)
public class EmployeeAvailabilityEntity extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "EMP_ID", referencedColumnName = "EMP_ID")
    private EmployeeEntity employee;

    @Column(name = "EVAT_DATETIME")
    private LocalDateTime dateTime;

    @Column(name = "EVAT_CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "EVAT_UPDATED_AT")
    private LocalDateTime updatedAt;
}
