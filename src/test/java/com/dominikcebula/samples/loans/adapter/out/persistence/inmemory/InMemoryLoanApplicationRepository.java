package com.dominikcebula.samples.loans.adapter.out.persistence.inmemory;

import com.dominikcebula.samples.loans.application.domain.model.loan.LoanApplication;
import com.dominikcebula.samples.loans.application.port.in.dto.ApplicantDTO;
import com.dominikcebula.samples.loans.application.port.in.dto.EmploymentDTO;
import com.dominikcebula.samples.loans.application.port.in.dto.LoanApplicationDTO;
import com.dominikcebula.samples.loans.application.port.in.dto.mapper.LoanApplicationMapper;
import com.dominikcebula.samples.loans.application.port.out.persistence.LoanApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Profile("in-memory-persistence")
@Primary
@RequiredArgsConstructor
public class InMemoryLoanApplicationRepository implements LoanApplicationRepository {
    private final Map<Long, LoanApplicationDTO> loanApplicationsData = new HashMap<>();
    private final Map<Long, ApplicantDTO> applicantsData = new HashMap<>();
    private final Map<Long, EmploymentDTO> employmentData = new HashMap<>();
    private final LoanApplicationMapper mapper;

    @Override
    public List<LoanApplication> findAll() {
        return loanApplicationsData.values().stream()
                .map(mapper::loanApplicationDTOToLoanApplication)
                .toList();
    }

    @Override
    public Optional<LoanApplication> findById(Long id) {
        return Optional.ofNullable(loanApplicationsData.get(id))
                .map(mapper::loanApplicationDTOToLoanApplication);
    }

    @Override
    public LoanApplication save(LoanApplication loanApplication) {
        LoanApplicationDTO loanApplicationDTO = mapper.loanApplicationToLoanApplicationDTO(loanApplication);
        ApplicantDTO applicantDTO = loanApplicationDTO.applicant();
        EmploymentDTO employmentDTO = applicantDTO.employment();

        long employmentId = getId(employmentDTO.id(), employmentData);
        EmploymentDTO storedEmployment = new EmploymentDTO(
                employmentId, employmentDTO.name(), employmentDTO.industry(), employmentDTO.yearlyIncome(),
                employmentDTO.numberOfEmployees(), employmentDTO.email(), employmentDTO.phoneNumber(), employmentDTO.website()
        );
        employmentData.put(employmentId, storedEmployment);

        long applicantId = getId(applicantDTO.id(), applicantsData);
        ApplicantDTO storedApplicant = new ApplicantDTO(
                applicantId, applicantDTO.firstName(), applicantDTO.lastName(),
                applicantDTO.birthDate(), applicantDTO.creditScore(),
                storedEmployment, applicantDTO.email(), applicantDTO.phoneNumber()
        );
        applicantsData.put(applicantId, storedApplicant);

        long loanApplicationId = getId(loanApplicationDTO.id(), loanApplicationsData);
        LoanApplicationDTO storedLoanApplication = new LoanApplicationDTO(
                loanApplicationId, storedApplicant,
                loanApplicationDTO.amount(), loanApplicationDTO.termsInMonths(),
                loanApplicationDTO.status()
        );
        loanApplicationsData.put(loanApplicationId, storedLoanApplication);

        return mapper.loanApplicationDTOToLoanApplication(storedLoanApplication);
    }

    private Long getId(Long currentId, Map<Long, ?> data) {
        return Objects.requireNonNullElseGet(currentId, () -> (long) data.size() + 1);
    }
}
