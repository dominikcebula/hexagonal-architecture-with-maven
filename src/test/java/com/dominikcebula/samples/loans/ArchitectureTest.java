package com.dominikcebula.samples.loans;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

public class ArchitectureTest {

    private static final String BASE_PACKAGE = "com.dominikcebula.samples.loans";
    private static final String DOMAIN_PACKAGE = BASE_PACKAGE + ".application.domain..";
    private static final String SERVICE_PACKAGE = BASE_PACKAGE + ".application.domain.service..";
    private static final String MODEL_PACKAGE = BASE_PACKAGE + ".application.domain.model..";
    private static final String ADAPTER_PACKAGE = BASE_PACKAGE + ".adapter..";
    private static final String INBOUND_PORTS_PACKAGE = BASE_PACKAGE + ".application.port.in..";
    private static final String OUTBOUND_PORTS_PACKAGE = BASE_PACKAGE + ".application.port.out..";
    private static final String INBOUND_ADAPTERS_PACKAGE = BASE_PACKAGE + ".adapter.in..";
    private static final String OUTBOUND_ADAPTERS_PACKAGE = BASE_PACKAGE + ".adapter.out..";

    private final JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages(BASE_PACKAGE);


    @Test
    void packagesShouldBeFreeOfCycles() {
        ArchRule rule = slices().matching(BASE_PACKAGE + ".(*)..")
                .should().beFreeOfCycles();

        rule.check(importedClasses);
    }

    @Test
    void domainShouldNotDependOnAdapters() {
        ArchRule rule = noClasses().that().resideInAPackage(DOMAIN_PACKAGE)
                .should().dependOnClassesThat().resideInAPackage(ADAPTER_PACKAGE);

        rule.check(importedClasses);
    }

    @Test
    void modelShouldBeIndependent() {
        ArchRule rule = noClasses().that().resideInAPackage(MODEL_PACKAGE)
                .should().dependOnClassesThat().resideInAnyPackage(
                        SERVICE_PACKAGE,
                        ADAPTER_PACKAGE,
                        INBOUND_PORTS_PACKAGE,
                        OUTBOUND_PORTS_PACKAGE,
                        INBOUND_ADAPTERS_PACKAGE,
                        OUTBOUND_ADAPTERS_PACKAGE
                );

        rule.check(importedClasses);
    }

    @Test
    void inboundAdaptersShouldDependOnlyOnInboundPorts() {
        ArchRule rule = noClasses().that().resideInAPackage(INBOUND_ADAPTERS_PACKAGE)
                .should().dependOnClassesThat().resideInAnyPackage(
                        SERVICE_PACKAGE,
                        MODEL_PACKAGE,
                        OUTBOUND_PORTS_PACKAGE,
                        OUTBOUND_ADAPTERS_PACKAGE
                );

        rule.check(importedClasses);
    }

    @Test
    void outboundAdaptersShouldDependOnlyOnOutboundPorts() {
        ArchRule rule = noClasses().that().resideInAPackage(OUTBOUND_ADAPTERS_PACKAGE)
                .should().dependOnClassesThat().resideInAnyPackage(
                        SERVICE_PACKAGE,
                        INBOUND_ADAPTERS_PACKAGE
                );

        rule.check(importedClasses);
    }

    @Test
    void inboundAdaptersShouldNotDependOnOutboundAdapters() {
        ArchRule rule = noClasses().that().resideInAPackage(INBOUND_ADAPTERS_PACKAGE)
                .should().dependOnClassesThat().resideInAPackage(OUTBOUND_ADAPTERS_PACKAGE);

        rule.check(importedClasses);
    }

    @Test
    void outboundAdaptersShouldNotDependOnInboundAdapters() {
        ArchRule rule = noClasses().that().resideInAPackage(OUTBOUND_ADAPTERS_PACKAGE)
                .should().dependOnClassesThat().resideInAPackage(INBOUND_ADAPTERS_PACKAGE);

        rule.check(importedClasses);
    }

    @Test
    void inboundPortsShouldNotDependOnOutboundPorts() {
        ArchRule rule = noClasses().that().resideInAPackage(INBOUND_PORTS_PACKAGE)
                .should().dependOnClassesThat().resideInAPackage(OUTBOUND_PORTS_PACKAGE);

        rule.check(importedClasses);
    }

    @Test
    void outboundPortsShouldNotDependOnInboundPorts() {
        ArchRule rule = noClasses().that().resideInAPackage(OUTBOUND_PORTS_PACKAGE)
                .should().dependOnClassesThat().resideInAPackage(INBOUND_PORTS_PACKAGE);

        rule.check(importedClasses);
    }
}
