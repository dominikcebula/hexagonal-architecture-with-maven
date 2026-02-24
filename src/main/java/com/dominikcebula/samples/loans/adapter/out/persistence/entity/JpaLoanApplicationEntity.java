package com.dominikcebula.samples.loans.adapter.out.persistence.entity;

import com.dominikcebula.samples.loans.application.domain.model.loan.LoanStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "loan_applications")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class JpaLoanApplicationEntity {
    @Id
    @SequenceGenerator(name = "loan_applications_seq", sequenceName = "loan_applications_seq", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "loan_applications_seq")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "applicant_id")
    private JpaApplicantEntity applicant;
    private BigDecimal amount;
    private String currency;
    private int termsInMonths;
    private LoanStatus status;
}
