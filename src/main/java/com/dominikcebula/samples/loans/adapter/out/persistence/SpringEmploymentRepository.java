package com.dominikcebula.samples.loans.adapter.out.persistence;

import com.dominikcebula.samples.loans.adapter.out.persistence.entity.JpaEmploymentEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface SpringEmploymentRepository extends ListCrudRepository<JpaEmploymentEntity, Long> {
}
