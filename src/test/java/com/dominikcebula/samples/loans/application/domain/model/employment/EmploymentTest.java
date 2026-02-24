package com.dominikcebula.samples.loans.application.domain.model.employment;

import com.dominikcebula.samples.loans.application.domain.model.contact.Email;
import com.dominikcebula.samples.loans.application.domain.model.contact.PhoneNumber;
import com.dominikcebula.samples.loans.application.domain.model.contact.Website;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class EmploymentTest {

    @Test
    public void shouldInitializeWithEmptyIdentifierWhenUsingConstructorWithoutIdentifier() {
        // Given
        EmployerName name = new EmployerName("TechCorp");
        Industry industry = new Industry("Technology");
        Money yearlyIncome = Money.of(1000000, "USD");
        NumberOfEmployees numberOfEmployees = new NumberOfEmployees(500);
        Email email = new Email("contact@techcorp.com");
        PhoneNumber phoneNumber = new PhoneNumber("+123456789");
        Website website = new Website("www.techcorp.com");

        // When
        Employment employment = new Employment(name, industry, yearlyIncome, numberOfEmployees, email, phoneNumber, website);

        // Then
        assertThat(employment.getIdentifier())
                .isNotNull();
        assertThat(employment.getIdentifier().getValue())
                .isEmpty();
    }

    @Test
    public void shouldInitializeAllPropertiesCorrectlyWhenUsingConstructorWithoutIdentifier() {
        // Given
        EmployerName name = new EmployerName("TechCorp");
        Industry industry = new Industry("Technology");
        Money yearlyIncome = Money.of(1000000, "USD");
        NumberOfEmployees numberOfEmployees = new NumberOfEmployees(500);
        Email email = new Email("contact@techcorp.com");
        PhoneNumber phoneNumber = new PhoneNumber("+123456789");
        Website website = new Website("www.techcorp.com");

        // When
        Employment employment = new Employment(name, industry, yearlyIncome, numberOfEmployees, email, phoneNumber, website);

        // Then
        assertThat(employment.getName()).isEqualTo(name);
        assertThat(employment.getIndustry()).isEqualTo(industry);
        assertThat(employment.getYearlyIncome()).isEqualTo(yearlyIncome);
        assertThat(employment.getNumberOfEmployees()).isEqualTo(numberOfEmployees);
        assertThat(employment.getEmail()).isEqualTo(email);
        assertThat(employment.getPhoneNumber()).isEqualTo(phoneNumber);
        assertThat(employment.getWebsite()).isEqualTo(website);
    }

    @ParameterizedTest
    @CsvSource({
            "2001, true",
            "2000, false",
            "1999, false"
    })
    public void shouldEvaluateStabilityBasedOnNumberOfEmployees(int numberOfEmployeesValue, boolean expectedStability) {
        // Given
        EmployerName name = new EmployerName("TechCorp");
        Industry industry = new Industry("Technology");
        Money yearlyIncome = Money.of(1000000, "USD");
        NumberOfEmployees numberOfEmployees = new NumberOfEmployees(numberOfEmployeesValue);
        Email email = new Email("contact@techcorp.com");
        PhoneNumber phoneNumber = new PhoneNumber("+123456789");
        Website website = new Website("www.techcorp.com");

        Employment employment = new Employment(name, industry, yearlyIncome, numberOfEmployees, email, phoneNumber, website);

        // When
        boolean result = employment.isStableEmployer();

        // Then
        assertThat(result)
                .as(String.format("Employer with %d employees should be %sstable.", numberOfEmployeesValue, expectedStability ? "" : "not "))
                .isEqualTo(expectedStability);
    }
}