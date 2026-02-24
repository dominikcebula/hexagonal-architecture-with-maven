package com.dominikcebula.samples.loans.adapter.out.persistence.mapper;

import com.dominikcebula.samples.loans.adapter.out.persistence.entity.JpaEmploymentEntity;
import com.dominikcebula.samples.loans.application.domain.model.contact.Email;
import com.dominikcebula.samples.loans.application.domain.model.contact.PhoneNumber;
import com.dominikcebula.samples.loans.application.domain.model.contact.Website;
import com.dominikcebula.samples.loans.application.domain.model.employment.EmployerName;
import com.dominikcebula.samples.loans.application.domain.model.employment.Employment;
import com.dominikcebula.samples.loans.application.domain.model.employment.Industry;
import com.dominikcebula.samples.loans.application.domain.model.employment.NumberOfEmployees;
import com.dominikcebula.samples.loans.application.domain.model.identifier.Identifier;
import com.dominikcebula.samples.loans.application.port.in.dto.mapper.IdentifierMapper;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ObjectFactory;
import org.springframework.stereotype.Component;

import static com.dominikcebula.samples.loans.adapter.out.persistence.mapper.JpaEmploymentMapper.EmploymentFromJpaFactory;

@Mapper(componentModel = "spring", uses = {IdentifierMapper.class, EmploymentFromJpaFactory.class, JpaMoneyMapper.class})
public interface JpaEmploymentMapper {
    @Mapping(source = "identifier", target = "id")
    @Mapping(source = "name.value", target = "name")
    @Mapping(source = "industry.value", target = "industry")
    @Mapping(source = "yearlyIncome.currency.currencyCode", target = "currency")
    @Mapping(source = "numberOfEmployees.value", target = "numberOfEmployees")
    @Mapping(source = "email.value", target = "email")
    @Mapping(source = "phoneNumber.value", target = "phoneNumber")
    @Mapping(source = "website.value", target = "website")
    JpaEmploymentEntity employmentToJpaEmployment(Employment employment);

    Employment jpaEmploymentEntityToEmployment(JpaEmploymentEntity jpaEmploymentEntity);

    @Component
    @RequiredArgsConstructor
    class EmploymentFromJpaFactory {
        private final JpaMoneyMapper jpaMoneyMapper;

        @ObjectFactory
        Employment createEmployment(JpaEmploymentEntity jpaEmploymentEntity) {
            return new Employment(
                    new Identifier(jpaEmploymentEntity.getId()),
                    new EmployerName(jpaEmploymentEntity.getName()),
                    new Industry(jpaEmploymentEntity.getIndustry()),
                    jpaMoneyMapper.mapJpaMoneyToMoney(jpaEmploymentEntity.getYearlyIncome(), jpaEmploymentEntity.getCurrency()),
                    new NumberOfEmployees(jpaEmploymentEntity.getNumberOfEmployees()),
                    new Email(jpaEmploymentEntity.getEmail()),
                    new PhoneNumber(jpaEmploymentEntity.getPhoneNumber()),
                    new Website(jpaEmploymentEntity.getWebsite())
            );
        }
    }
}
