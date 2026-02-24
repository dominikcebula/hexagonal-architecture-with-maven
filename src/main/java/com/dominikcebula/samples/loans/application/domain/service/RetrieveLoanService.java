package com.dominikcebula.samples.loans.application.domain.service;

import com.dominikcebula.samples.loans.application.port.in.RetrieveLoanUseCase;
import com.dominikcebula.samples.loans.application.port.in.dto.LoanApplicationDTO;
import com.dominikcebula.samples.loans.application.port.in.dto.mapper.LoanApplicationMapper;
import com.dominikcebula.samples.loans.application.port.out.persistence.LoanApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RetrieveLoanService implements RetrieveLoanUseCase {
    private final LoanApplicationRepository loanApplicationRepository;
    private final LoanApplicationMapper mapper;

    @Override
    public Optional<LoanApplicationDTO> retrieveLoanApplication(Long id) {
        return loanApplicationRepository.findById(id)
                .map(mapper::loanApplicationToLoanApplicationDTO);
    }
}
