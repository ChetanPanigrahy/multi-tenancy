package com.chetan.service.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import com.chetan.service.domain.entity.Organisation;

import java.util.Optional;

@Repository
public interface OrganisationRepository extends Neo4jRepository<Organisation, String> {

    Optional<Organisation> findByTenantIdAndOrgId(String tenantId, String orgId);
}
