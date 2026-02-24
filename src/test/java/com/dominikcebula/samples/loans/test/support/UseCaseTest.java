package com.dominikcebula.samples.loans.test.support;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

@Target({TYPE})
@Retention(RUNTIME)
@SpringBootTest(webEnvironment = NONE, classes = UseCaseTestConfiguration.class)
@ActiveProfiles("in-memory-persistence")
public @interface UseCaseTest {
}

@Configuration
@ComponentScan(basePackages = {
        "com.dominikcebula.samples.loans.application",
        "com.dominikcebula.samples.loans.adapter.out.persistence.inmemory"
})
class UseCaseTestConfiguration {
}
