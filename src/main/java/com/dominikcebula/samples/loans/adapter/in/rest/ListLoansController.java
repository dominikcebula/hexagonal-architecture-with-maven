package com.dominikcebula.samples.loans.adapter.in.rest;

import com.dominikcebula.samples.loans.application.port.in.ListLoansUseCase;
import com.dominikcebula.samples.loans.application.port.in.dto.LoanApplicationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.dominikcebula.samples.loans.adapter.in.rest.ApiConstants.API_BASE;

@RestController
@RequestMapping(API_BASE)
@RequiredArgsConstructor
public class ListLoansController {
    private final ListLoansUseCase listLoansUseCase;

    @GetMapping
    public ResponseEntity<List<LoanApplicationDTO>> retrieveLoanApplications() {
        List<LoanApplicationDTO> loanApplicationsDTOs = listLoansUseCase.retrieveLoanApplications();

        return ResponseEntity.ok(loanApplicationsDTOs);
    }
}
