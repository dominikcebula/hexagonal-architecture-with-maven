package com.dominikcebula.samples.loans.adapter.out.persistence;

import com.dominikcebula.samples.loans.adapter.out.persistence.entity.JpaLoanApplicationEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface SpringLoanApplicationRepository extends ListCrudRepository<JpaLoanApplicationEntity, Long> {
}
