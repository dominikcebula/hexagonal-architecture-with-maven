package com.dominikcebula.samples.loans.application.port.in.dto.builder;

import com.dominikcebula.samples.loans.application.domain.model.contact.Email;
import com.dominikcebula.samples.loans.application.domain.model.contact.PhoneNumber;
import com.dominikcebula.samples.loans.application.domain.model.contact.Website;
import com.dominikcebula.samples.loans.application.domain.model.employment.EmployerName;
import com.dominikcebula.samples.loans.application.domain.model.employment.Employment;
import com.dominikcebula.samples.loans.application.domain.model.employment.Industry;
import com.dominikcebula.samples.loans.application.domain.model.employment.NumberOfEmployees;
import com.dominikcebula.samples.loans.application.domain.model.identifier.Identifier;
import org.javamoney.moneta.Money;

public class EmploymentBuilder {

    private Identifier identifier = Identifier.empty();
    private EmployerName name = new EmployerName("Tech Solutions Inc.");
    private Industry industry = new Industry("Information Technology");
    private Money yearlyIncome = Money.of(95000, "USD");
    private NumberOfEmployees numberOfEmployees = new NumberOfEmployees(2500);
    private Email email = new Email("hr@techsolutions.com");
    private PhoneNumber phoneNumber = new PhoneNumber("+1-415-555-0123");
    private Website website = new Website("https://techsolutions.com");

    private EmploymentBuilder() {
    }

    public static EmploymentBuilder newEmployment() {
        return new EmploymentBuilder();
    }

    public EmploymentBuilder withIdentifier(Identifier identifier) {
        this.identifier = identifier;
        return this;
    }

    public EmploymentBuilder withName(EmployerName name) {
        this.name = name;
        return this;
    }

    public EmploymentBuilder withIndustry(Industry industry) {
        this.industry = industry;
        return this;
    }

    public EmploymentBuilder withYearlyIncome(Money yearlyIncome) {
        this.yearlyIncome = yearlyIncome;
        return this;
    }

    public EmploymentBuilder withNumberOfEmployees(NumberOfEmployees numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
        return this;
    }

    public EmploymentBuilder withEmail(Email email) {
        this.email = email;
        return this;
    }

    public EmploymentBuilder withPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public EmploymentBuilder withWebsite(Website website) {
        this.website = website;
        return this;
    }

    public Employment build() {
        return new Employment(identifier, name, industry, yearlyIncome, numberOfEmployees, email, phoneNumber, website);
    }
}