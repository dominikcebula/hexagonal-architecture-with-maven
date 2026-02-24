package com.dominikcebula.samples.loans.adapter.out.persistence;

import com.dominikcebula.samples.loans.adapter.out.persistence.entity.JpaApplicantEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface SpringApplicantRepository extends ListCrudRepository<JpaApplicantEntity, Long> {
}
