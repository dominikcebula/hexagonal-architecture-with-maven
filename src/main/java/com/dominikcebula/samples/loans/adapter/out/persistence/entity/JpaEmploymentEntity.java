package com.dominikcebula.samples.loans.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "employment_info")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class JpaEmploymentEntity {
    @Id
    @SequenceGenerator(name = "employment_info_seq", sequenceName = "employment_info_seq", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "employment_info_seq")
    private Long id;
    private String name;
    private String industry;
    private BigDecimal yearlyIncome;
    private String currency;
    private int numberOfEmployees;
    private String email;
    private String phoneNumber;
    private String website;
}
