package com.chetan.management.service;

public interface TenantManagementService {

    void createTenant(String tenantId, String schema, String orgId, String module);
}