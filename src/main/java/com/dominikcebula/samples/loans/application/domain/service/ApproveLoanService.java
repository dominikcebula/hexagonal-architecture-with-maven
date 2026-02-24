package com.dominikcebula.samples.loans.application.domain.service;

import com.dominikcebula.samples.loans.application.port.in.ApproveLoanUseCase;
import com.dominikcebula.samples.loans.application.port.in.dto.mapper.LoanApplicationMapper;
import com.dominikcebula.samples.loans.application.port.out.persistence.LoanApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.dominikcebula.samples.loans.application.domain.model.loan.LoanApplication.LoanApprovalResult;

@Service
@RequiredArgsConstructor
public class ApproveLoanService implements ApproveLoanUseCase {
    private final LoanApplicationRepository repository;
    private final LoanApplicationMapper mapper;

    @Override
    public LoanApprovalAnswer approveLoan(Long id) {
        return repository.findById(id)
                .map(loanApplication -> {
                    LoanApprovalResult loanApprovalResult = loanApplication.approve();

                    loanApplication = repository.save(loanApplication);

                    return switch (loanApprovalResult.getStatus()) {
                        case APPROVED ->
                                LoanApprovalAnswer.approved(mapper.loanApplicationToLoanApplicationDTO(loanApplication));
                        case REJECTED -> LoanApprovalAnswer.rejected(
                                loanApprovalResult.getValidationResults().getMessages(),
                                mapper.loanApplicationToLoanApplicationDTO(loanApplication));
                        default ->
                                throw new IllegalStateException("Unexpected loan approval result: " + loanApprovalResult.getStatus());
                    };
                })
                .orElse(LoanApprovalAnswer.loanNotFound());
    }
}
