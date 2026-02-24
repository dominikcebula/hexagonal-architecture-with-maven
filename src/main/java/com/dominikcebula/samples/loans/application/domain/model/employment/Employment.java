package com.dominikcebula.samples.loans.application.domain.model.employment;

import com.dominikcebula.samples.loans.application.domain.model.contact.Email;
import com.dominikcebula.samples.loans.application.domain.model.contact.PhoneNumber;
import com.dominikcebula.samples.loans.application.domain.model.contact.Website;
import com.dominikcebula.samples.loans.application.domain.model.identifier.Identifier;
import com.dominikcebula.samples.loans.application.domain.model.support.validation.Validation;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.javamoney.moneta.Money;

@Getter
@EqualsAndHashCode
@ToString
public class Employment {
    private final Identifier identifier;
    private final EmployerName name;
    private final Industry industry;
    private final Money yearlyIncome;
    private final NumberOfEmployees numberOfEmployees;
    private final Email email;
    private final PhoneNumber phoneNumber;
    private final Website website;

    public Employment(EmployerName name, Industry industry, Money yearlyIncome, NumberOfEmployees numberOfEmployees, Email email, PhoneNumber phoneNumber, Website website) {
        this(Identifier.empty(), name, industry, yearlyIncome, numberOfEmployees, email, phoneNumber, website);
    }

    public Employment(Identifier identifier, EmployerName name, Industry industry, Money yearlyIncome, NumberOfEmployees numberOfEmployees, Email email, PhoneNumber phoneNumber, Website website) {
        Validation.requireNonNull(identifier, "Identifier cannot be null.");
        Validation.requireNonNull(name, "Name cannot be null.");
        Validation.requireNonNull(industry, "Industry cannot be null.");
        Validation.requireNonNull(yearlyIncome, "Yearly income cannot be null.");
        Validation.requireNonNull(numberOfEmployees, "Number of employees cannot be null.");
        Validation.requireNonNull(email, "Email cannot be null.");
        Validation.requireNonNull(phoneNumber, "PhoneNumber cannot be null.");
        Validation.requireNonNull(website, "Website cannot be null.");

        this.identifier = identifier;
        this.name = name;
        this.industry = industry;
        this.yearlyIncome = yearlyIncome;
        this.numberOfEmployees = numberOfEmployees;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.website = website;
    }

    public boolean isStableEmployer() {
        return numberOfEmployees.getValue() > 2000;
    }
}
