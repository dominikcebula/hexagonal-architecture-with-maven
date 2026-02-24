package com.dominikcebula.samples.loans.application.port.in.dto.mapper;

import com.dominikcebula.samples.loans.application.domain.model.contact.Email;
import com.dominikcebula.samples.loans.application.domain.model.contact.PhoneNumber;
import com.dominikcebula.samples.loans.application.domain.model.identifier.Identifier;
import com.dominikcebula.samples.loans.application.domain.model.loan.Applicant;
import com.dominikcebula.samples.loans.application.domain.model.loan.CreditScore;
import com.dominikcebula.samples.loans.application.domain.model.person.BirthDate;
import com.dominikcebula.samples.loans.application.domain.model.person.FirstName;
import com.dominikcebula.samples.loans.application.domain.model.person.LastName;
import com.dominikcebula.samples.loans.application.port.in.dto.ApplicantDTO;
import com.dominikcebula.samples.loans.application.port.in.dto.ApplicantRegistrationDTO;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ObjectFactory;
import org.springframework.stereotype.Component;

import static com.dominikcebula.samples.loans.application.port.in.dto.mapper.ApplicantMapper.ApplicantFactory;

@Mapper(componentModel = "spring", uses = {IdentifierMapper.class, EmploymentMapper.class, ApplicantFactory.class})
public interface ApplicantMapper {
    @Mapping(source = "identifier", target = "id")
    @Mapping(source = "firstName.value", target = "firstName")
    @Mapping(source = "lastName.value", target = "lastName")
    @Mapping(source = "birthDate.value", target = "birthDate")
    @Mapping(source = "creditScore.value", target = "creditScore")
    @Mapping(source = "email.value", target = "email")
    @Mapping(source = "phoneNumber.value", target = "phoneNumber")
    ApplicantDTO applicantToApplicantDTO(Applicant applicant);

    Applicant applicantDTOToApplicant(ApplicantDTO applicantTO);

    Applicant applicantRegistrationDTOToApplicant(ApplicantRegistrationDTO applicantRegistrationDTO);

    @Component
    @RequiredArgsConstructor
    class ApplicantFactory {
        private final EmploymentMapper employmentMapper;

        @ObjectFactory
        Applicant createApplicant(ApplicantDTO applicantDTO) {
            return new Applicant(
                    new Identifier(applicantDTO.id()),
                    new FirstName(applicantDTO.firstName()),
                    new LastName(applicantDTO.lastName()),
                    new BirthDate(applicantDTO.birthDate()),
                    new CreditScore(applicantDTO.creditScore()),
                    employmentMapper.employmentDTOToEmployment(applicantDTO.employment()),
                    new Email(applicantDTO.email()),
                    new PhoneNumber(applicantDTO.phoneNumber())
            );
        }

        @ObjectFactory
        Applicant createApplicant(ApplicantRegistrationDTO applicantRegistrationDTO) {
            return new Applicant(
                    new FirstName(applicantRegistrationDTO.firstName()),
                    new LastName(applicantRegistrationDTO.lastName()),
                    new BirthDate(applicantRegistrationDTO.birthDate()),
                    new CreditScore(applicantRegistrationDTO.creditScore()),
                    employmentMapper.employmentRegistrationDTOToEmployment(applicantRegistrationDTO.employment()),
                    new Email(applicantRegistrationDTO.email()),
                    new PhoneNumber(applicantRegistrationDTO.phoneNumber())
            );
        }
    }
}
