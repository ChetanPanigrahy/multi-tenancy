package com.chetan.management.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.chetan.management.service.TenantManagementService;
import com.chetan.service.model.TenantValue;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/")
public class TenantsApiController {

    private final TenantManagementService tenantManagementService;

    @PostMapping("/tenants")
    public ResponseEntity<Void> createTenant(@Valid @RequestBody TenantValue tenant) {
        this.tenantManagementService.createTenant(tenant.getTenantId(), tenant.getSchema(), tenant.getOrgId(), tenant.getModule());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
