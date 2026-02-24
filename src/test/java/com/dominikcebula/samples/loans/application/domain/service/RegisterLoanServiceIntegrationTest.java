package com.dominikcebula.samples.loans.application.domain.service;

import com.dominikcebula.samples.loans.application.port.in.RegisterLoanUseCase;
import com.dominikcebula.samples.loans.application.port.in.dto.LoanApplicationDTO;
import com.dominikcebula.samples.loans.application.port.in.dto.LoanApplicationRegistrationDTO;
import com.dominikcebula.samples.loans.test.support.UseCaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;

import static com.dominikcebula.samples.loans.application.domain.model.loan.LoanStatus.REGISTERED;
import static com.dominikcebula.samples.loans.application.port.in.dto.builder.LoanApplicationRegistrationDTOBuilder.newLoanApplicationRegistration;
import static org.assertj.core.api.Assertions.assertThat;

@UseCaseTest
class RegisterLoanServiceIntegrationTest {
    @Autowired
    private RegisterLoanUseCase registerLoanUseCase;

    @Test
    @DirtiesContext
    void shouldRegisterLoanApplication() {
        // given
        LoanApplicationRegistrationDTO loanApplicationRegistrationDTO = newLoanApplicationRegistration().build();

        // when
        LoanApplicationDTO registeredLoanApplication = registerLoanUseCase.registerLoanApplication(loanApplicationRegistrationDTO);

        // then
        assertThat(registeredLoanApplication)
                .usingRecursiveComparison()
                .ignoringFieldsMatchingRegexes(".*id", "status")
                .isEqualTo(loanApplicationRegistrationDTO);
        assertThat(registeredLoanApplication.status())
                .isEqualTo(REGISTERED);
    }
}