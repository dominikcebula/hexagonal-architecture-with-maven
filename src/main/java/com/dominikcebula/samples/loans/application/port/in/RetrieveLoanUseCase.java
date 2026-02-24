package com.dominikcebula.samples.loans.application.port.in;

import com.dominikcebula.samples.loans.application.port.in.dto.LoanApplicationDTO;

import java.util.Optional;

public interface RetrieveLoanUseCase {
    Optional<LoanApplicationDTO> retrieveLoanApplication(Long id);
}
