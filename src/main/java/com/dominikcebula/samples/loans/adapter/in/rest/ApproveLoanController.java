package com.dominikcebula.samples.loans.adapter.in.rest;

import com.dominikcebula.samples.loans.application.port.in.ApproveLoanUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.dominikcebula.samples.loans.adapter.in.rest.ApiConstants.API_BASE;
import static com.dominikcebula.samples.loans.application.port.in.ApproveLoanUseCase.LoanApprovalAnswer;
import static com.dominikcebula.samples.loans.application.port.in.ApproveLoanUseCase.LoanApprovalAnswerStatus.NOT_FOUND;

@RestController
@RequestMapping(API_BASE)
@RequiredArgsConstructor
public class ApproveLoanController {
    private final ApproveLoanUseCase approveLoanUseCase;

    @PostMapping("{id}/approve")
    public ResponseEntity<LoanApprovalAnswer> retrieveLoanApplication(@PathVariable("id") Long id) {
        LoanApprovalAnswer loanApprovalAnswer = approveLoanUseCase.approveLoan(id);

        if (loanApprovalAnswer.getStatus() == NOT_FOUND)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(loanApprovalAnswer);
    }
}
