package com.chetan.management.repository;

import com.chetan.management.domain.entity.Tenant;
import org.springframework.data.repository.CrudRepository;

public interface TenantRepository extends CrudRepository<Tenant, String> {
}