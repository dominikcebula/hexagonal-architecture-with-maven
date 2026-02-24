package com.dominikcebula.samples.loans.application.domain.model.loan;

import com.dominikcebula.samples.loans.application.domain.model.contact.Email;
import com.dominikcebula.samples.loans.application.domain.model.contact.PhoneNumber;
import com.dominikcebula.samples.loans.application.domain.model.employment.Employment;
import com.dominikcebula.samples.loans.application.domain.model.identifier.Identifier;
import com.dominikcebula.samples.loans.application.domain.model.person.Age;
import com.dominikcebula.samples.loans.application.domain.model.person.BirthDate;
import com.dominikcebula.samples.loans.application.domain.model.person.FirstName;
import com.dominikcebula.samples.loans.application.domain.model.person.LastName;
import com.dominikcebula.samples.loans.application.domain.model.support.spring.BeanProvider;
import com.dominikcebula.samples.loans.application.domain.model.support.validation.Validation;
import com.dominikcebula.samples.loans.application.port.time.CurrentDateProvider;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import static java.time.temporal.ChronoUnit.YEARS;

@Getter
@EqualsAndHashCode
@ToString
public class Applicant {
    private final Identifier identifier;
    private final FirstName firstName;
    private final LastName lastName;
    private final BirthDate birthDate;
    private final CreditScore creditScore;
    private final Employment employment;
    private final Email email;
    private final PhoneNumber phoneNumber;

    private final CurrentDateProvider currentDateProvider = BeanProvider.getBean(CurrentDateProvider.class);

    public Applicant(FirstName firstName, LastName lastName, BirthDate birthDate, CreditScore creditScore, Employment employment, Email email, PhoneNumber phoneNumber) {
        this(Identifier.empty(), firstName, lastName, birthDate, creditScore, employment, email, phoneNumber);
    }

    public Applicant(Identifier identifier, FirstName firstName, LastName lastName, BirthDate birthDate, CreditScore creditScore, Employment employment, Email email, PhoneNumber phoneNumber) {
        Validation.requireNonNull(identifier, "Identifier must not be null.");
        Validation.requireNonNull(firstName, "First Name must not be null.");
        Validation.requireNonNull(lastName, "Last Name must not be null.");
        Validation.requireNonNull(birthDate, "Birth date must not be null.");
        Validation.requireNonNull(creditScore, "Credit score must not be null.");
        Validation.requireNonNull(employment, "Employment must not be null.");
        Validation.requireNonNull(email, "Email must not be null.");
        Validation.requireNonNull(phoneNumber, "Phone number must not be null.");

        this.identifier = identifier;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.creditScore = creditScore;
        this.employment = employment;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Age getAge() {
        return new Age(YEARS.between(birthDate.getValue(), currentDateProvider.now()));
    }
}
