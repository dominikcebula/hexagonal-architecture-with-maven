package com.dominikcebula.samples.loans.application.port.in.dto.assertions;

import com.dominikcebula.samples.loans.application.port.in.dto.LoanApplicationDTO;
import com.dominikcebula.samples.loans.application.port.in.dto.LoanApplicationRegistrationDTO;

import static org.assertj.core.api.Assertions.assertThat;

public class LoanApplicationDTOAssertions {
    public static void assertEquals(LoanApplicationDTO loanApplicationDTO, LoanApplicationRegistrationDTO loanApplicationRegistrationDTO) {
        assertThat(loanApplicationDTO)
                .usingRecursiveComparison()
                .ignoringFieldsMatchingRegexes(".*id", "status")
                .isEqualTo(loanApplicationRegistrationDTO);
        assertThat(loanApplicationDTO.id())
                .isPositive();
        assertThat(loanApplicationDTO.applicant().id())
                .isPositive();
        assertThat(loanApplicationDTO.applicant().employment().id())
                .isPositive();
    }
}
