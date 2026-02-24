package com.dominikcebula.samples.loans.adapter.in.rest;

import com.dominikcebula.samples.loans.application.port.in.RetrieveLoanUseCase;
import com.dominikcebula.samples.loans.application.port.in.dto.LoanApplicationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static com.dominikcebula.samples.loans.adapter.in.rest.ApiConstants.API_BASE;

@RestController
@RequestMapping(API_BASE)
@RequiredArgsConstructor
public class RetrieveLoanController {
    private final RetrieveLoanUseCase retrieveLoanUseCase;

    @GetMapping("{id}")
    public ResponseEntity<LoanApplicationDTO> retrieveLoanApplication(@PathVariable("id") Long id) {
        Optional<LoanApplicationDTO> loanApplicationDTO = retrieveLoanUseCase.retrieveLoanApplication(id);

        return loanApplicationDTO
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
