package com.dominikcebula.samples.loans.adapter.out.persistence.mapper;

import com.dominikcebula.samples.loans.adapter.out.persistence.entity.JpaLoanApplicationEntity;
import com.dominikcebula.samples.loans.adapter.out.persistence.mapper.JpaApplicantMapper.ApplicantFromJpaFactory;
import com.dominikcebula.samples.loans.application.domain.model.identifier.Identifier;
import com.dominikcebula.samples.loans.application.domain.model.loan.LoanAmount;
import com.dominikcebula.samples.loans.application.domain.model.loan.LoanApplication;
import com.dominikcebula.samples.loans.application.domain.model.loan.TermsInMonths;
import com.dominikcebula.samples.loans.application.port.in.dto.mapper.IdentifierMapper;
import com.dominikcebula.samples.loans.application.port.in.dto.mapper.MoneyMapper;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ObjectFactory;
import org.springframework.stereotype.Component;

import static com.dominikcebula.samples.loans.adapter.out.persistence.mapper.JpaLoanApplicationMapper.LoanApplicationFromJpaFactory;

@Mapper(componentModel = "spring", uses = {LoanApplicationFromJpaFactory.class, JpaApplicantMapper.class, IdentifierMapper.class, MoneyMapper.class, JpaMoneyMapper.class})
public interface JpaLoanApplicationMapper {
    @Mapping(source = "identifier", target = "id")
    @Mapping(source = "amount.value", target = "amount")
    @Mapping(source = "amount.value.currency.currencyCode", target = "currency")
    @Mapping(source = "termsInMonths.value", target = "termsInMonths")
    JpaLoanApplicationEntity loanApplicationEntityToJpaLoanApplication(LoanApplication loanApplication);

    LoanApplication jpaLoanApplicationEntityToLoanApplication(JpaLoanApplicationEntity jpaLoanApplicationEntity);

    @Component
    @RequiredArgsConstructor
    class LoanApplicationFromJpaFactory {
        private final ApplicantFromJpaFactory applicantMapper;
        private final JpaMoneyMapper jpaMoneyMapper;

        @ObjectFactory
        LoanApplication createLoanApplication(JpaLoanApplicationEntity jpaLoanApplicationEntity) {
            return new LoanApplication(
                    new Identifier(jpaLoanApplicationEntity.getId()),
                    applicantMapper.createApplicant(jpaLoanApplicationEntity.getApplicant()),
                    new LoanAmount(jpaMoneyMapper.mapJpaMoneyToMoney(jpaLoanApplicationEntity.getAmount(), jpaLoanApplicationEntity.getCurrency())),
                    new TermsInMonths(jpaLoanApplicationEntity.getTermsInMonths()),
                    jpaLoanApplicationEntity.getStatus()
            );
        }
    }
}
