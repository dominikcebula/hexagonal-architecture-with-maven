package com.dominikcebula.samples.loans.adapter.in.rest.testutils;

import com.dominikcebula.samples.loans.application.port.in.LoanTestUtils;
import com.dominikcebula.samples.loans.application.port.in.dto.LoanApplicationDTO;
import com.dominikcebula.samples.loans.application.port.in.dto.LoanApplicationRegistrationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.dominikcebula.samples.loans.adapter.in.rest.ApiConstants.API_BASE;
import static com.dominikcebula.samples.loans.application.port.in.dto.builder.LoanApplicationRegistrationDTOBuilder.newLoanApplicationRegistration;
import static org.assertj.core.api.Assertions.assertThat;

@Component
@RequiredArgsConstructor
public class LoanRestTestUtils {
    private final TestRestTemplate testRestTemplate;
    private final LoanTestUtils loanTestUtils;

    public LoanApplicationDTO registerLoanApplication() {
        ResponseEntity<LoanApplicationDTO> response = testRestTemplate.postForEntity(API_BASE, newLoanApplicationRegistration().build(), LoanApplicationDTO.class);

        assertThat(response).extracting(ResponseEntity::getStatusCode).isEqualTo(HttpStatus.CREATED);

        return response.getBody();
    }

    public List<LoanApplicationDTO> registerLoanApplications() {
        List<LoanApplicationRegistrationDTO> loanApplicationRegistrations = loanTestUtils.createLoanApplicationRegistrations();

        List<ResponseEntity<LoanApplicationDTO>> responses = loanApplicationRegistrations.stream()
                .map(registrationDTO -> testRestTemplate.postForEntity(API_BASE, registrationDTO, LoanApplicationDTO.class))
                .toList();

        assertThat(responses).hasSize(loanApplicationRegistrations.size());
        assertThat(responses).extracting(ResponseEntity::getStatusCode).allMatch(HttpStatus.CREATED::equals);

        return responses.stream()
                .map(ResponseEntity::getBody)
                .toList();
    }
}
