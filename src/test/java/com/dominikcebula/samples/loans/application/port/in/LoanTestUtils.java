package com.dominikcebula.samples.loans.application.port.in;

import com.dominikcebula.samples.loans.application.domain.service.RegisterLoanService;
import com.dominikcebula.samples.loans.application.port.in.dto.LoanApplicationDTO;
import com.dominikcebula.samples.loans.application.port.in.dto.LoanApplicationRegistrationDTO;
import com.dominikcebula.samples.loans.application.port.in.dto.builder.ApplicantRegistrationDTOBuilder;
import com.dominikcebula.samples.loans.application.port.in.dto.builder.EmploymentRegistrationDTOBuilder;
import com.dominikcebula.samples.loans.application.port.in.dto.builder.MoneyDTOBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static com.dominikcebula.samples.loans.application.port.in.dto.builder.LoanApplicationRegistrationDTOBuilder.newLoanApplicationRegistration;

@Component
@RequiredArgsConstructor
public class LoanTestUtils {
    private final RegisterLoanService registerLoanService;

    public LoanApplicationDTO registerLoanApplication() {
        return registerLoanApplication(newLoanApplicationRegistration().build());
    }

    public LoanApplicationDTO registerLoanApplication(LoanApplicationRegistrationDTO loanApplicationRegistrationDTO) {
        return registerLoanService.registerLoanApplication(loanApplicationRegistrationDTO);
    }

    public List<LoanApplicationDTO> registerLoanApplications() {
        return createLoanApplicationRegistrations().stream()
                .map(registerLoanService::registerLoanApplication)
                .toList();
    }

    public List<LoanApplicationRegistrationDTO> createLoanApplicationRegistrations() {
        return List.of(
                newLoanApplicationRegistration()
                        .withApplicant(
                                ApplicantRegistrationDTOBuilder
                                        .newApplicantRegistration()
                                        .withEmail("John.Doe@mail.com")
                                        .withFirstName("John")
                                        .withLastName("Doe")
                                        .withCreditScore(100)
                                        .withBirthDate(LocalDate.of(1985, Month.FEBRUARY, 11))
                                        .withEmployment(
                                                EmploymentRegistrationDTOBuilder
                                                        .newEmploymentRegistration()
                                                        .withName("TechNova Solutions")
                                                        .withIndustry("Information Technology")
                                                        .withWebsite("https://www.technova.com")
                                                        .withEmail("contact@technova.com")
                                                        .withYearlyIncome(MoneyDTOBuilder.newMoney()
                                                                .withAmount(12500000)
                                                                .build())
                                                        .withNumberOfEmployees(240)
                                                        .build())
                                        .build())
                        .withAmount(MoneyDTOBuilder.newMoney()
                                .withAmount(10000)
                                .build())
                        .withTermsInMonths(10)
                        .build(),
                newLoanApplicationRegistration()
                        .withApplicant(
                                ApplicantRegistrationDTOBuilder
                                        .newApplicantRegistration()
                                        .withEmail("Emily.Carter@mail.com")
                                        .withFirstName("Emily")
                                        .withLastName("Carter")
                                        .withCreditScore(200)
                                        .withBirthDate(LocalDate.of(1983, Month.APRIL, 15))
                                        .withEmployment(
                                                EmploymentRegistrationDTOBuilder
                                                        .newEmploymentRegistration()
                                                        .withName("GreenField Logistics")
                                                        .withIndustry("Transportation and Logistics")
                                                        .withWebsite("https://www.greenfieldlogistics.com")
                                                        .withEmail("info@greenfieldlogistics.com")
                                                        .withYearlyIncome(MoneyDTOBuilder.newMoney()
                                                                .withAmount(8700000)
                                                                .build())
                                                        .withNumberOfEmployees(150)
                                                        .build())
                                        .build())
                        .withAmount(MoneyDTOBuilder.newMoney()
                                .withAmount(20000)
                                .build())
                        .withTermsInMonths(20)
                        .build(),
                newLoanApplicationRegistration()
                        .withApplicant(
                                ApplicantRegistrationDTOBuilder
                                        .newApplicantRegistration()
                                        .withEmail("James.Morgan@example.com")
                                        .withFirstName("James")
                                        .withLastName("Morgan")
                                        .withCreditScore(720)
                                        .withBirthDate(LocalDate.of(1990, Month.DECEMBER, 5))
                                        .withEmployment(
                                                EmploymentRegistrationDTOBuilder
                                                        .newEmploymentRegistration()
                                                        .withName("Aurum Financial Group")
                                                        .withIndustry("Financial Services")
                                                        .withWebsite("https://www.aurumfinance.com")
                                                        .withEmail("support@aurumfinance.com")
                                                        .withYearlyIncome(MoneyDTOBuilder.newMoney()
                                                                .withAmount(19800000)
                                                                .build())
                                                        .withNumberOfEmployees(320)
                                                        .build())
                                        .build())
                        .withAmount(MoneyDTOBuilder.newMoney()
                                .withAmount(50000)
                                .build())
                        .withTermsInMonths(36)
                        .build());
    }
}
