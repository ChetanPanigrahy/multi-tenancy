package com.chetan.service.repository;

import com.chetan.service.domain.entity.Role;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends Neo4jRepository<Role, String> {
}
