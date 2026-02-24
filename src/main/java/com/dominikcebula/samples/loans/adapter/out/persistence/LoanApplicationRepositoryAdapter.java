package com.dominikcebula.samples.loans.adapter.out.persistence;

import com.dominikcebula.samples.loans.adapter.out.persistence.entity.JpaLoanApplicationEntity;
import com.dominikcebula.samples.loans.adapter.out.persistence.mapper.JpaLoanApplicationMapper;
import com.dominikcebula.samples.loans.application.domain.model.loan.LoanApplication;
import com.dominikcebula.samples.loans.application.port.out.persistence.LoanApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class LoanApplicationRepositoryAdapter implements LoanApplicationRepository {
    private final SpringLoanApplicationRepository loanApplicationRepository;
    private final SpringApplicantRepository applicantRepository;
    private final SpringEmploymentRepository employmentRepository;

    private final JpaLoanApplicationMapper mapper;

    @Override
    public List<LoanApplication> findAll() {
        return loanApplicationRepository.findAll().stream()
                .map(mapper::jpaLoanApplicationEntityToLoanApplication)
                .toList();
    }

    @Override
    public Optional<LoanApplication> findById(Long id) {
        return loanApplicationRepository.findById(id)
                .map(mapper::jpaLoanApplicationEntityToLoanApplication);
    }

    @Override
    public LoanApplication save(LoanApplication loanApplication) {
        JpaLoanApplicationEntity jpaLoanApplicationEntity = mapper.loanApplicationEntityToJpaLoanApplication(loanApplication);

        jpaLoanApplicationEntity.getApplicant().setEmployment(employmentRepository.save(jpaLoanApplicationEntity.getApplicant().getEmployment()));
        jpaLoanApplicationEntity.setApplicant(applicantRepository.save(jpaLoanApplicationEntity.getApplicant()));
        jpaLoanApplicationEntity = loanApplicationRepository.save(jpaLoanApplicationEntity);

        return mapper.jpaLoanApplicationEntityToLoanApplication(jpaLoanApplicationEntity);
    }
}
