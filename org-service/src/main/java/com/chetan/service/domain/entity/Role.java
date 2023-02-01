package com.chetan.service.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node("Role")
@Getter
@Setter
@Builder
public class Role {
    @Id
    private String roleId;
    private String roleType;
}
