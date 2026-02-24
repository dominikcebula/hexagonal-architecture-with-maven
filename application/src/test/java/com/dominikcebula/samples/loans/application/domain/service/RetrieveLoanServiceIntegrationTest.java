package com.dominikcebula.samples.loans.application.domain.service;

import com.dominikcebula.samples.loans.application.port.in.LoanTestUtils;
import com.dominikcebula.samples.loans.application.port.in.RetrieveLoanUseCase;
import com.dominikcebula.samples.loans.application.port.in.dto.LoanApplicationDTO;
import com.dominikcebula.samples.loans.test.support.UseCaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@UseCaseTest
class RetrieveLoanServiceIntegrationTest {
    @Autowired
    private LoanTestUtils loanTestUtils;
    @Autowired
    private RetrieveLoanUseCase retrieveLoanUseCase;

    @Test
    @DirtiesContext
    void shouldRetrieveRegisteredLoan() {
        // given
        LoanApplicationDTO registeredLoanApplication = loanTestUtils.registerLoanApplication();

        // when
        Optional<LoanApplicationDTO> retrievedLoanApplication = retrieveLoanUseCase.retrieveLoanApplication(registeredLoanApplication.id());

        // then
        assertThat(retrievedLoanApplication.isPresent()).isTrue();
        assertThat(retrievedLoanApplication.get()).isEqualTo(registeredLoanApplication);
    }

    @Test
    void shouldNotRetrieveNonExitingLoan() {
        // when
        Optional<LoanApplicationDTO> retrievedLoanApplication = retrieveLoanUseCase.retrieveLoanApplication(Long.MAX_VALUE);

        // then
        assertThat(retrievedLoanApplication.isPresent()).isFalse();
    }
}