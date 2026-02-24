package com.dominikcebula.samples.loans.application.port.in;

import com.dominikcebula.samples.loans.application.port.in.dto.LoanApplicationDTO;

import java.util.List;

public interface ListLoansUseCase {
    List<LoanApplicationDTO> retrieveLoanApplications();
}
