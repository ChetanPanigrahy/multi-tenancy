package com.chetan.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
@FeignClient(value = "tenant-management-service-client", url = "http://localhost:8088")
public interface TenantManagementServiceClient {
    @RequestMapping(
            method = RequestMethod.POST,
            path = "/tenants?tenantId={tenantId}&schema={tenantId}"
    )
    ResponseEntity<Void> addNewTenant(@PathVariable("tenantId") String tenantId);
}
