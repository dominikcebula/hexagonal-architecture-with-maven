package com.dominikcebula.samples.loans.adapter.out.persistence.mapper;

import com.dominikcebula.samples.loans.adapter.out.persistence.entity.JpaApplicantEntity;
import com.dominikcebula.samples.loans.application.domain.model.contact.Email;
import com.dominikcebula.samples.loans.application.domain.model.contact.PhoneNumber;
import com.dominikcebula.samples.loans.application.domain.model.identifier.Identifier;
import com.dominikcebula.samples.loans.application.domain.model.loan.Applicant;
import com.dominikcebula.samples.loans.application.domain.model.loan.CreditScore;
import com.dominikcebula.samples.loans.application.domain.model.person.BirthDate;
import com.dominikcebula.samples.loans.application.domain.model.person.FirstName;
import com.dominikcebula.samples.loans.application.domain.model.person.LastName;
import com.dominikcebula.samples.loans.application.port.in.dto.mapper.IdentifierMapper;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ObjectFactory;
import org.springframework.stereotype.Component;

import static com.dominikcebula.samples.loans.adapter.out.persistence.mapper.JpaApplicantMapper.ApplicantFromJpaFactory;

@Mapper(componentModel = "spring", uses = {IdentifierMapper.class, JpaEmploymentMapper.class, ApplicantFromJpaFactory.class})
public interface JpaApplicantMapper {
    @Mapping(source = "identifier", target = "id")
    @Mapping(source = "firstName.value", target = "firstName")
    @Mapping(source = "lastName.value", target = "lastName")
    @Mapping(source = "birthDate.value", target = "birthDate")
    @Mapping(source = "creditScore.value", target = "creditScore")
    @Mapping(source = "email.value", target = "email")
    @Mapping(source = "phoneNumber.value", target = "phoneNumber")
    JpaApplicantEntity applicantToJpaApplicant(Applicant applicant);

    Applicant jpaApplicantEntityToApplicant(JpaApplicantEntity jpaApplicantEntity);

    @Component
    @RequiredArgsConstructor
    class ApplicantFromJpaFactory {
        private final JpaEmploymentMapper jpaEmploymentMapper;

        @ObjectFactory
        Applicant createApplicant(JpaApplicantEntity jpaApplicantEntity) {
            return new Applicant(
                    new Identifier(jpaApplicantEntity.getId()),
                    new FirstName(jpaApplicantEntity.getFirstName()),
                    new LastName(jpaApplicantEntity.getLastName()),
                    new BirthDate(jpaApplicantEntity.getBirthDate()),
                    new CreditScore(jpaApplicantEntity.getCreditScore()),
                    jpaEmploymentMapper.jpaEmploymentEntityToEmployment(jpaApplicantEntity.getEmployment()),
                    new Email(jpaApplicantEntity.getEmail()),
                    new PhoneNumber(jpaApplicantEntity.getPhoneNumber())
            );
        }
    }
}
