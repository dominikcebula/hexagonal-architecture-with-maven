package com.dominikcebula.samples.loans.adapter.in.rest;

import com.dominikcebula.samples.loans.application.port.in.dto.LoanApplicationDTO;
import com.dominikcebula.samples.loans.application.port.in.dto.LoanApplicationRegistrationDTO;
import com.dominikcebula.samples.loans.application.port.in.dto.assertions.LoanApplicationDTOAssertions;
import com.dominikcebula.samples.loans.test.support.ApiTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static com.dominikcebula.samples.loans.adapter.in.rest.ApiConstants.API_BASE;
import static com.dominikcebula.samples.loans.application.port.in.dto.builder.LoanApplicationRegistrationDTOBuilder.newLoanApplicationRegistration;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpHeaders.LOCATION;
import static org.springframework.http.HttpStatus.CREATED;

@ApiTest
class RegisterLoanControllerIntegrationTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @DirtiesContext
    void shouldRegisterLoan() {
        // given
        LoanApplicationRegistrationDTO applicationRegistrationDTO = newLoanApplicationRegistration().build();

        // when
        ResponseEntity<LoanApplicationDTO> response = testRestTemplate.postForEntity(API_BASE, applicationRegistrationDTO, LoanApplicationDTO.class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getHeaders())
                .containsEntry(LOCATION, List.of(API_BASE + "/" + response.getBody().id()));
        LoanApplicationDTOAssertions.assertEquals(response.getBody(), applicationRegistrationDTO);
    }
}
