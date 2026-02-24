package com.dominikcebula.samples.loans.application.domain.service;

import com.dominikcebula.samples.loans.application.port.in.ListLoansUseCase;
import com.dominikcebula.samples.loans.application.port.in.dto.LoanApplicationDTO;
import com.dominikcebula.samples.loans.application.port.in.dto.mapper.LoanApplicationMapper;
import com.dominikcebula.samples.loans.application.port.out.persistence.LoanApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListLoansService implements ListLoansUseCase {
    private final LoanApplicationRepository loanApplicationRepository;
    private final LoanApplicationMapper mapper;

    @Override
    public List<LoanApplicationDTO> retrieveLoanApplications() {
        return loanApplicationRepository.findAll()
                .stream()
                .map(mapper::loanApplicationToLoanApplicationDTO)
                .toList();
    }
}
