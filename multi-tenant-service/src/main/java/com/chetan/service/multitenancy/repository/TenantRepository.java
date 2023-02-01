package com.chetan.service.multitenancy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.chetan.service.multitenancy.domain.entity.Tenant;

import java.util.Optional;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, String> {
    @Query("select t from Tenant t where t.tenantId = :tenantId and t.module = :module")
    Optional<Tenant> findByTenantAndModuleId(@Param("tenantId") String tenantId,
                                             @Param("module") String module);
}
