package com.chetan.service.client;

import com.chetan.service.model.TenantValue;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
@FeignClient(value = "tenant-management-service-client", url = "http://localhost:9001")
public interface TenantManagementServiceClient {
    @RequestMapping(
            method = RequestMethod.POST,
            path = "/tenants"
    )
    ResponseEntity<Void> addNewTenant(TenantValue tenant);
}
