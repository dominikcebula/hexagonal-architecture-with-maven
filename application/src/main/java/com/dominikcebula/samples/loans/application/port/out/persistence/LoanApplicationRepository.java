package com.dominikcebula.samples.loans.application.port.out.persistence;

import com.dominikcebula.samples.loans.application.domain.model.loan.LoanApplication;

import java.util.List;
import java.util.Optional;

public interface LoanApplicationRepository {
    List<LoanApplication> findAll();

    Optional<LoanApplication> findById(Long id);

    LoanApplication save(LoanApplication loanApplication);
}
