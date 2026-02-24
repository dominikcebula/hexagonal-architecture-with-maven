package com.dominikcebula.samples.loans.application.port.in;

import com.dominikcebula.samples.loans.application.port.in.dto.LoanApplicationDTO;
import com.dominikcebula.samples.loans.application.port.in.dto.LoanApplicationRegistrationDTO;

public interface RegisterLoanUseCase {
    LoanApplicationDTO registerLoanApplication(LoanApplicationRegistrationDTO loanApplicationRegistrationDTO);
}
