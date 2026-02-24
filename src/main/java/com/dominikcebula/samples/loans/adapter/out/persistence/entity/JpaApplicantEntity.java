package com.dominikcebula.samples.loans.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "applicants")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class JpaApplicantEntity {
    @Id
    @SequenceGenerator(name = "applicants_seq", sequenceName = "applicants_seq", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "applicants_seq")
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private int creditScore;
    @ManyToOne
    @JoinColumn(name = "employment_info_id")
    private JpaEmploymentEntity employment;
    private String email;
    private String phoneNumber;
}
