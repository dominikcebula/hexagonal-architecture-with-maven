package com.dominikcebula.samples.loans.application.domain.service;

import com.dominikcebula.samples.loans.application.domain.model.loan.LoanStatus;
import com.dominikcebula.samples.loans.application.port.in.ApproveLoanUseCase;
import com.dominikcebula.samples.loans.application.port.in.ApproveLoanUseCase.LoanApprovalAnswerStatus;
import com.dominikcebula.samples.loans.application.port.in.LoanTestUtils;
import com.dominikcebula.samples.loans.application.port.in.dto.LoanApplicationDTO;
import com.dominikcebula.samples.loans.application.port.in.dto.builder.ApplicantRegistrationDTOBuilder;
import com.dominikcebula.samples.loans.application.port.in.dto.mapper.LoanApplicationMapper;
import com.dominikcebula.samples.loans.application.port.out.persistence.LoanApplicationRepository;
import com.dominikcebula.samples.loans.application.port.time.CurrentDateProvider;
import com.dominikcebula.samples.loans.application.port.time.MockDateProvider;
import com.dominikcebula.samples.loans.test.support.UseCaseTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;

import static com.dominikcebula.samples.loans.application.port.in.ApproveLoanUseCase.LoanApprovalAnswer;
import static com.dominikcebula.samples.loans.application.port.in.dto.builder.LoanApplicationRegistrationDTOBuilder.newLoanApplicationRegistration;
import static org.assertj.core.api.Assertions.assertThat;

@UseCaseTest
class ApproveLoanServiceIntegrationTest {
    @Autowired
    private LoanTestUtils loanTestUtils;
    @Autowired
    private ApproveLoanUseCase approveLoanUseCase;
    @Autowired
    private LoanApplicationRepository repository;
    @Autowired
    private LoanApplicationMapper mapper;
    @Autowired
    private CurrentDateProvider currentDateProvider;

    @BeforeEach
    void setUp() {
        MockDateProvider.setUp();
    }

    @AfterEach
    void tearDown() {
        MockDateProvider.tearDown();
    }

    @Test
    @DirtiesContext
    void shouldApproveLoan() {
        // given
        LoanApplicationDTO registerLoanApplicationDTO = loanTestUtils.registerLoanApplication();

        // when
        LoanApprovalAnswer loanApprovalAnswer = approveLoanUseCase.approveLoan(registerLoanApplicationDTO.id());

        // then
        assertLoanApproved(loanApprovalAnswer);
        assertLoanAnswer(loanApprovalAnswer, registerLoanApplicationDTO);
        assertLoanStored(loanApprovalAnswer, registerLoanApplicationDTO);
    }

    @Test
    @DirtiesContext
    void shouldNotApproveLoanForApplicantThatIsNotOfLegalAge() {
        // given
        LoanApplicationDTO loanApplicationDTO = loanTestUtils.registerLoanApplication(
                newLoanApplicationRegistration()
                        .withApplicant(
                                ApplicantRegistrationDTOBuilder
                                        .newApplicantRegistration()
                                        .withBirthDate(birthDateForAgeOfFive())
                                        .build()
                        )
                        .build());

        // when
        LoanApprovalAnswer loanApprovalAnswer = approveLoanUseCase.approveLoan(loanApplicationDTO.id());

        // then
        assertLoanRejected(loanApprovalAnswer);
        assertLoanAnswer(loanApprovalAnswer, loanApplicationDTO);
        assertLoanStored(loanApprovalAnswer, loanApplicationDTO);
    }

    private void assertLoanApproved(LoanApprovalAnswer approvalAnswer) {
        assertLoanStatus(approvalAnswer, LoanApprovalAnswerStatus.APPROVED, LoanStatus.APPROVED);
        assertThat(approvalAnswer.getValidationErrorMessages()).isEmpty();
    }

    private void assertLoanRejected(LoanApprovalAnswer approvalAnswer) {
        assertLoanStatus(approvalAnswer, LoanApprovalAnswerStatus.REJECTED, LoanStatus.REJECTED);
        assertThat(approvalAnswer.getValidationErrorMessages()).contains("Applicant is not of legal age.");
    }

    private void assertLoanAnswer(LoanApprovalAnswer approvalAnswer, LoanApplicationDTO registerLoanApplicationDTO) {
        assertThat(approvalAnswer.getLoanApplication().isPresent()).isTrue();
        assertThat(approvalAnswer.getLoanApplication().get())
                .usingRecursiveComparison()
                .ignoringFields("status")
                .isEqualTo(registerLoanApplicationDTO);
        assertThat(registerLoanApplicationDTO.status())
                .isEqualTo(LoanStatus.REGISTERED);
    }

    private void assertLoanStatus(LoanApprovalAnswer approvalAnswer, LoanApprovalAnswerStatus expectedAnswerStatus, LoanStatus expectedLoanStatus) {
        assertThat(approvalAnswer.getStatus()).isEqualTo(expectedAnswerStatus);
        assertThat(approvalAnswer.getLoanApplication().isPresent()).isTrue();
        assertThat(approvalAnswer.getLoanApplication().get().status())
                .isEqualTo(expectedLoanStatus);
    }

    private void assertLoanStored(LoanApprovalAnswer approvalAnswer, LoanApplicationDTO registerLoanApplicationDTO) {
        assertThat(approvalAnswer.getLoanApplication().isPresent()).isTrue();
        assertThat(approvalAnswer.getLoanApplication().get())
                .isEqualTo(mapper.loanApplicationToLoanApplicationDTO(
                        repository.findById(registerLoanApplicationDTO.id()).orElseThrow()));
    }

    private LocalDate birthDateForAgeOfFive() {
        return currentDateProvider.now().minusYears(5);
    }
}
