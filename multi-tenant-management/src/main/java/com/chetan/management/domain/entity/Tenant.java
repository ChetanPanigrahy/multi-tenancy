package com.chetan.management.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Tenant {
    @Id
    @Size(max = 100)
    @Column(name = "schema")
    private String schema;

    @Size(max = 30)
    @Column(name = "tenant_id")
    private String tenantId;

    @Size(max = 30)
    @Column(name = "org_id")
    private String orgId;

    @Size(max = 30)
    @Column(name = "module")
    private String module;
}