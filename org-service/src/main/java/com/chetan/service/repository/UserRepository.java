package com.chetan.service.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import com.chetan.service.domain.entity.User;

import java.util.Optional;

public interface UserRepository extends Neo4jRepository<User, String> {

    @Query(value = "MATCH(u:User)-[:USER_OF]-(o:Organisation{orgId::orgId, tenantId::tenantId}) RETURN u")
    Optional<User> findByOrgIdAndTenantId(@Param("orgId") String orgId, @Param("tenantId") String tenantId);
}
