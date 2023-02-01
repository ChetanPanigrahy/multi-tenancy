package com.chetan.service.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Node("User")
@Getter
@Setter
@Builder
public class User {
    @Id
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    @Relationship(type = "IN_ROLE", direction = Relationship.Direction.OUTGOING)
    private List<Role> roles;
}
