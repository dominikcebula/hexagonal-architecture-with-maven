package com.dominikcebula.samples.loans.application.domain.model.loan;

import com.dominikcebula.samples.loans.application.domain.model.contact.Email;
import com.dominikcebula.samples.loans.application.domain.model.contact.PhoneNumber;
import com.dominikcebula.samples.loans.application.domain.model.contact.Website;
import com.dominikcebula.samples.loans.application.domain.model.employment.EmployerName;
import com.dominikcebula.samples.loans.application.domain.model.employment.Employment;
import com.dominikcebula.samples.loans.application.domain.model.employment.Industry;
import com.dominikcebula.samples.loans.application.domain.model.employment.NumberOfEmployees;
import com.dominikcebula.samples.loans.application.domain.model.identifier.Identifier;
import com.dominikcebula.samples.loans.application.domain.model.person.Age;
import com.dominikcebula.samples.loans.application.domain.model.person.BirthDate;
import com.dominikcebula.samples.loans.application.domain.model.person.FirstName;
import com.dominikcebula.samples.loans.application.domain.model.person.LastName;
import com.dominikcebula.samples.loans.application.domain.model.support.validation.DomainValidationException;
import com.dominikcebula.samples.loans.application.port.time.MockDateProvider;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicantTest {

    @BeforeEach
    void setUp() {
        MockDateProvider.setUp();
    }

    @AfterEach
    void tearDown() {
        MockDateProvider.tearDown();
    }

    @Test
    void shouldCalculateAgeCorrectly() {
        // given
        LocalDate birthDate = birthDateForAgeOfFive();
        Applicant applicant = new Applicant(FIRST_NAME, LAST_NAME, new BirthDate(birthDate), CREDIT_SCORE, EMPLOYMENT, E_MAIL, PHONE_NUMBER);

        // when
        Age age = applicant.getAge();

        // then
        assertThat(age.getValue()).isEqualTo(5);
    }

    @Test
    void shouldCreateApplicantWithValidInputs() {
        // when
        Applicant applicant = new Applicant(FIRST_NAME, LAST_NAME, BRITH_DATE, CREDIT_SCORE, EMPLOYMENT, E_MAIL, PHONE_NUMBER);

        // then
        assertThat(applicant.getIdentifier()).isNotNull();
        assertThat(applicant.getIdentifier().getValue()).isEmpty();
        assertThat(applicant.getFirstName()).isEqualTo(FIRST_NAME);
        assertThat(applicant.getLastName()).isEqualTo(LAST_NAME);
        assertThat(applicant.getBirthDate()).isEqualTo(BRITH_DATE);
        assertThat(applicant.getCreditScore()).isEqualTo(CREDIT_SCORE);
        assertThat(applicant.getEmployment()).isEqualTo(EMPLOYMENT);
        assertThat(applicant.getEmail()).isEqualTo(E_MAIL);
        assertThat(applicant.getPhoneNumber()).isEqualTo(PHONE_NUMBER);
    }

    @Test
    void shouldCreateApplicantWithIdentifier() {
        // when
        Applicant applicant = new Applicant(IDENTIFIER, FIRST_NAME, LAST_NAME, BRITH_DATE, CREDIT_SCORE, EMPLOYMENT, E_MAIL, PHONE_NUMBER);

        // then
        assertThat(applicant.getIdentifier()).isEqualTo(IDENTIFIER);
        assertThat(applicant.getIdentifier().getValue()).isEqualTo(Optional.of(IDENTIFIER_VALUE));
        assertThat(applicant.getFirstName()).isEqualTo(FIRST_NAME);
        assertThat(applicant.getLastName()).isEqualTo(LAST_NAME);
        assertThat(applicant.getBirthDate()).isEqualTo(BRITH_DATE);
        assertThat(applicant.getCreditScore()).isEqualTo(CREDIT_SCORE);
        assertThat(applicant.getEmployment()).isEqualTo(EMPLOYMENT);
        assertThat(applicant.getEmail()).isEqualTo(E_MAIL);
        assertThat(applicant.getPhoneNumber()).isEqualTo(PHONE_NUMBER);
    }

    @ParameterizedTest
    @MethodSource("nullParametersProvider")
    void shouldThrowExceptionWhenParameterIsNull(Object nullParameter, String readableParameterName) {
        // when/then
        assertThatThrownBy(() -> new Applicant(
                nullParameterIfRequired(nullParameter, IDENTIFIER),
                nullParameterIfRequired(nullParameter, FIRST_NAME),
                nullParameterIfRequired(nullParameter, LAST_NAME),
                nullParameterIfRequired(nullParameter, BRITH_DATE),
                nullParameterIfRequired(nullParameter, CREDIT_SCORE),
                nullParameterIfRequired(nullParameter, EMPLOYMENT),
                nullParameterIfRequired(nullParameter, E_MAIL),
                nullParameterIfRequired(nullParameter, PHONE_NUMBER)
        ))
                .isInstanceOf(DomainValidationException.class)
                .hasMessage(readableParameterName + " must not be null.");
    }

    private static Stream<Arguments> nullParametersProvider() {
        return Stream.of(
                Arguments.of(IDENTIFIER, "Identifier"),
                Arguments.of(FIRST_NAME, "First Name"),
                Arguments.of(LAST_NAME, "Last Name"),
                Arguments.of(BRITH_DATE, "Birth date"),
                Arguments.of(CREDIT_SCORE, "Credit score"),
                Arguments.of(EMPLOYMENT, "Employment"),
                Arguments.of(E_MAIL, "Email"),
                Arguments.of(PHONE_NUMBER, "Phone number")
        );
    }

    private static <T> T nullParameterIfRequired(Object nullParameter, T parameter) {
        return nullParameter == parameter ? null : parameter;
    }

    private LocalDate birthDateForAgeOfFive() {
        return new MockDateProvider().now().minusYears(5);
    }

    private static Employment createEmployment() {
        EmployerName employerName = new EmployerName("TechCorp");
        Industry industry = new Industry("Technology");
        Money yearlyIncome = Money.of(100000, "USD");
        NumberOfEmployees numberOfEmployees = new NumberOfEmployees(500);
        Email employerEmail = new Email("contact@techcorp.com");
        PhoneNumber employerPhoneNumber = new PhoneNumber("+987654321");
        Website website = new Website("www.techcorp.com");

        return new Employment(employerName, industry, yearlyIncome, numberOfEmployees, employerEmail, employerPhoneNumber, website);
    }

    private static final Long IDENTIFIER_VALUE = 123L;
    private static final String FIRST_NAME_VALUE = "John";
    private static final String LAST_NAME_VALUE = "Doe";
    private static final LocalDate BIRTH_DATE_VALUE = LocalDate.of(1990, 1, 1);
    private static final int CREDIT_SCORE_VALUE = 750;
    private static final String EMAIL_VALUE = "john.doe@example.com";
    private static final String PHONE_NUMBER_VALUE = "+123456789";

    private static final Identifier IDENTIFIER = new Identifier(IDENTIFIER_VALUE);
    private static final FirstName FIRST_NAME = new FirstName(FIRST_NAME_VALUE);
    private static final LastName LAST_NAME = new LastName(LAST_NAME_VALUE);
    private static final BirthDate BRITH_DATE = new BirthDate(BIRTH_DATE_VALUE);
    private static final CreditScore CREDIT_SCORE = new CreditScore(CREDIT_SCORE_VALUE);
    private static final Employment EMPLOYMENT = createEmployment();
    private static final Email E_MAIL = new Email(EMAIL_VALUE);
    private static final PhoneNumber PHONE_NUMBER = new PhoneNumber(PHONE_NUMBER_VALUE);
}