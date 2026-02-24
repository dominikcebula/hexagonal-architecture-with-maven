package com.dominikcebula.samples.loans.application.port.in;

import com.dominikcebula.samples.loans.application.port.in.dto.LoanApplicationDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.Optional;

import static com.dominikcebula.samples.loans.application.port.in.ApproveLoanUseCase.LoanApprovalAnswerStatus.*;
import static java.util.Collections.emptyList;

public interface ApproveLoanUseCase {
    LoanApprovalAnswer approveLoan(Long id);

    @Getter
    @EqualsAndHashCode
    @NoArgsConstructor
    @ToString
    class LoanApprovalAnswer {
        private LoanApprovalAnswerStatus status;
        private List<String> validationErrorMessages = emptyList();
        private LoanApplicationDTO loanApplication;

        private LoanApprovalAnswer(LoanApprovalAnswerStatus status) {
            this.status = status;
        }

        private LoanApprovalAnswer(LoanApprovalAnswerStatus status, LoanApplicationDTO loanApplication) {
            this(status);
            this.loanApplication = loanApplication;
        }

        private LoanApprovalAnswer(LoanApprovalAnswerStatus status, List<String> validationErrorMessages, LoanApplicationDTO loanApplication) {
            this(status, loanApplication);
            this.validationErrorMessages = validationErrorMessages;
        }

        public static LoanApprovalAnswer approved(LoanApplicationDTO loanApplicationDTO) {
            return new LoanApprovalAnswer(APPROVED, loanApplicationDTO);
        }

        public static LoanApprovalAnswer rejected(List<String> validationMessages, LoanApplicationDTO loanApplicationDTO) {
            return new LoanApprovalAnswer(REJECTED, validationMessages, loanApplicationDTO);
        }

        public static LoanApprovalAnswer loanNotFound() {
            return new LoanApprovalAnswer(NOT_FOUND);
        }

        public Optional<LoanApplicationDTO> getLoanApplication() {
            return Optional.ofNullable(loanApplication);
        }
    }

    enum LoanApprovalAnswerStatus {
        APPROVED,
        REJECTED,
        NOT_FOUND
    }
}
