package com.dominikcebula.samples.loans.test.support;

import com.dominikcebula.samples.loans.adapter.out.persistence.PostgreSQLContainerConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@Target({TYPE})
@Retention(RUNTIME)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@Testcontainers
@Import({PostgreSQLContainerConfiguration.class})
public @interface ApiTest {
}
