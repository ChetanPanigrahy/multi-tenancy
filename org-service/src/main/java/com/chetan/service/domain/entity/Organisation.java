package com.chetan.service.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Node("Organisation")
@Getter
@Setter
@Builder
public class Organisation {
    @Id
    private String orgId;
    private String tenantId;
    private String orgName;
    @Relationship(type = "USER_OF", direction = Relationship.Direction.INCOMING)
    private List<User> users;
    @Relationship(type = "MODULE_OF", direction = Relationship.Direction.INCOMING)
    private List<Module> modules;
    @Relationship(type = "HAS_ROLE", direction = Relationship.Direction.OUTGOING)
    private List<Role> roles;
}
