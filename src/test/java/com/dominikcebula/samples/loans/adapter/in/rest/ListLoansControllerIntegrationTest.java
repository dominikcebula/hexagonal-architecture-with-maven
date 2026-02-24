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
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.OK;

@ApiTest
class ListLoansControllerIntegrationTest {
    @Autowired
    private LoanRestTestUtils loanRestTestUtils;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DirtiesContext
    void shouldListLoanApplications() {
        // given
        List<LoanApplicationDTO> registeredLoanApplications = loanRestTestUtils.registerLoanApplications();

        // when
        ResponseEntity<LoanApplicationDTO[]> response = restTemplate.getForEntity(API_BASE, LoanApplicationDTO[].class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(OK);
        assertThat(response.getBody())
                .containsOnlyElementsOf(registeredLoanApplications);
    }

    @Test
    void shouldListEmptyLoanApplications() {
        // when
        ResponseEntity<LoanApplicationDTO[]> response = restTemplate.getForEntity(API_BASE, LoanApplicationDTO[].class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(OK);
        assertThat(response.getBody()).isEmpty();
    }
}