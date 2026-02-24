package com.dominikcebula.samples.loans.application.domain.service;

import com.dominikcebula.samples.loans.application.port.in.ListLoansUseCase;
import com.dominikcebula.samples.loans.application.port.in.LoanTestUtils;
import com.dominikcebula.samples.loans.application.port.in.dto.LoanApplicationDTO;
import com.dominikcebula.samples.loans.test.support.UseCaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@UseCaseTest
class ListLoansServiceIntegrationTest {
    @Autowired
    private LoanTestUtils loanTestUtils;
    @Autowired
    private ListLoansUseCase listLoansUseCase;

    @Test
    @DirtiesContext
    void shouldListLoanApplications() {
        // given
        List<LoanApplicationDTO> registeredLoanApplications = loanTestUtils.registerLoanApplications();

        // when
        List<LoanApplicationDTO> retrievedLoanApplications = listLoansUseCase.retrieveLoanApplications();

        // then
        assertThat(retrievedLoanApplications)
                .isEqualTo(registeredLoanApplications);
    }

    @Test
    void shouldReturnEmptyListWhenNoLoanApplicationsRegistered() {
        // when
        List<LoanApplicationDTO> retrievedLoanApplications = listLoansUseCase.retrieveLoanApplications();

        // then
        assertThat(retrievedLoanApplications).isEmpty();
    }
}