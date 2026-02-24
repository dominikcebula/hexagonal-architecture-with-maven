package com.dominikcebula.samples.loans.adapter.in.rest;

import com.dominikcebula.samples.loans.adapter.in.rest.testutils.LoanRestTestUtils;
import com.dominikcebula.samples.loans.application.port.in.dto.LoanApplicationDTO;
import com.dominikcebula.samples.loans.test.support.ApiTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static com.dominikcebula.samples.loans.adapter.in.rest.ApiConstants.API_BASE;
import static com.dominikcebula.samples.loans.adapter.in.rest.common.http.uri.URIUtils.pathTo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@ApiTest
class RetrieveLoanControllerIntegrationTest {
    @Autowired
    private LoanRestTestUtils loanRestTestUtils;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DirtiesContext
    void shouldRetrieveLoanApplication() {
        // given
        List<LoanApplicationDTO> registeredLoanApplications = loanRestTestUtils.registerLoanApplications();
        LoanApplicationDTO registeredLoan = registeredLoanApplications.get(1);

        // when
        ResponseEntity<LoanApplicationDTO> response = restTemplate.getForEntity(pathTo(API_BASE, registeredLoan.id()), LoanApplicationDTO.class);

        // then
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(OK);
        assertThat(response.getBody().id()).isPositive();
        assertThat(response.getBody()).isEqualTo(registeredLoan);
    }

    @Test
    void shouldNotRetrieveNonExistingLoanApplication() {
        // when
        ResponseEntity<LoanApplicationDTO> response = restTemplate.getForEntity(pathTo(API_BASE, Integer.MAX_VALUE), LoanApplicationDTO.class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(NOT_FOUND);
        assertThat(response.getBody()).isNull();
    }
}
