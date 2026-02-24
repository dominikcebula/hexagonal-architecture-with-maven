package com.dominikcebula.samples.loans.adapter.in.rest;

import com.dominikcebula.samples.loans.adapter.in.rest.testutils.LoanRestTestUtils;
import com.dominikcebula.samples.loans.application.domain.model.loan.LoanStatus;
import com.dominikcebula.samples.loans.application.port.in.ApproveLoanUseCase.LoanApprovalAnswer;
import com.dominikcebula.samples.loans.application.port.in.ApproveLoanUseCase.LoanApprovalAnswerStatus;
import com.dominikcebula.samples.loans.application.port.in.dto.LoanApplicationDTO;
import com.dominikcebula.samples.loans.test.support.ApiTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import static com.dominikcebula.samples.loans.adapter.in.rest.ApiConstants.API_BASE;
import static com.dominikcebula.samples.loans.adapter.in.rest.common.http.uri.URIUtils.pathTo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@ApiTest
class ApproveLoanControllerIntegrationTest {
    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private LoanRestTestUtils loanRestTestUtils;

    @Test
    @DirtiesContext
    void shouldApproveLoan() {
        // given
        LoanApplicationDTO loanApplicationDTO = loanRestTestUtils.registerLoanApplication();
        long applicationId = loanApplicationDTO.id();

        // when
        ResponseEntity<LoanApprovalAnswer> response = testRestTemplate.postForEntity(pathTo(API_BASE, applicationId + "/approve"), null, LoanApprovalAnswer.class);

        // then
        assertResponseStatusOk(response);
        assertLoanApproved(response);
        assertNoErrorMessages(response);
    }

    @Test
    void shouldNotApproveNonExistingLoan() {
        // when
        ResponseEntity<LoanApprovalAnswer> response = testRestTemplate.postForEntity(pathTo(API_BASE, Integer.MAX_VALUE + "/approve"), null, LoanApprovalAnswer.class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(NOT_FOUND);
    }

    private void assertResponseStatusOk(ResponseEntity<LoanApprovalAnswer> response) {
        assertThat(response.getStatusCode()).isEqualTo(OK);
    }

    private void assertLoanApproved(ResponseEntity<LoanApprovalAnswer> response) {
        LoanApprovalAnswer loanApprovalAnswer = response.getBody();

        assertThat(loanApprovalAnswer).isNotNull();
        assertThat(loanApprovalAnswer.getStatus()).isEqualTo(LoanApprovalAnswerStatus.APPROVED);
        assertThat(loanApprovalAnswer.getLoanApplication().isPresent()).isTrue();
        assertThat(loanApprovalAnswer.getLoanApplication().get().status()).isEqualTo(LoanStatus.APPROVED);
    }

    private void assertNoErrorMessages(ResponseEntity<LoanApprovalAnswer> response) {
        LoanApprovalAnswer loanApprovalAnswer = response.getBody();

        assertThat(loanApprovalAnswer).isNotNull();
        assertThat(loanApprovalAnswer.getValidationErrorMessages()).isEmpty();
    }
}