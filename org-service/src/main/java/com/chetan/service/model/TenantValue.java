package com.chetan.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TenantValue {
    @JsonProperty("tenantId")
    private String tenantId;

    @JsonProperty("schema")
    private String schema;

    @JsonProperty("orgId")
    private String orgId;

    @JsonProperty("module")
    private String module;
}
