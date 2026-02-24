package com.dominikcebula.samples.loans.application.port.in.dto.builder;

import com.dominikcebula.samples.loans.application.domain.model.contact.Email;
import com.dominikcebula.samples.loans.application.domain.model.contact.PhoneNumber;
import com.dominikcebula.samples.loans.application.domain.model.employment.Employment;
import com.dominikcebula.samples.loans.application.domain.model.identifier.Identifier;
import com.dominikcebula.samples.loans.application.domain.model.loan.Applicant;
import com.dominikcebula.samples.loans.application.domain.model.loan.CreditScore;
import com.dominikcebula.samples.loans.application.domain.model.person.BirthDate;
import com.dominikcebula.samples.loans.application.domain.model.person.FirstName;
import com.dominikcebula.samples.loans.application.domain.model.person.LastName;

import java.time.LocalDate;

public class ApplicantBuilder {

    private Identifier identifier = Identifier.empty();
    private FirstName firstName = new FirstName("John");
    private LastName lastName = new LastName("Doe");
    private BirthDate birthDate = new BirthDate(LocalDate.of(1993, 8, 15));
    private CreditScore creditScore = new CreditScore(700);
    private Employment employment = EmploymentBuilder.newEmployment().build();
    private Email email = new Email("john.doe@example.com");
    private PhoneNumber phoneNumber = new PhoneNumber("1234567890");

    private ApplicantBuilder() {
    }

    public static ApplicantBuilder newApplicant() {
        return new ApplicantBuilder();
    }

    public ApplicantBuilder withIdentifier(Identifier identifier) {
        this.identifier = identifier;
        return this;
    }

    public ApplicantBuilder withFirstName(FirstName firstName) {
        this.firstName = firstName;
        return this;
    }

    public ApplicantBuilder withLastName(LastName lastName) {
        this.lastName = lastName;
        return this;
    }

    public ApplicantBuilder withBirthDate(BirthDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public ApplicantBuilder withCreditScore(CreditScore creditScore) {
        this.creditScore = creditScore;
        return this;
    }

    public ApplicantBuilder withEmployment(Employment employment) {
        this.employment = employment;
        return this;
    }

    public ApplicantBuilder withEmail(Email email) {
        this.email = email;
        return this;
    }

    public ApplicantBuilder withPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public Applicant build() {
        return new Applicant(identifier, firstName, lastName, birthDate, creditScore, employment, email, phoneNumber);
    }
}