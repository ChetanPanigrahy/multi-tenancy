package com.chetan.service.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.chetan.service.model.OrganisationValue;
import com.chetan.service.model.UserValue;
import com.chetan.service.multitenancy.util.TenantContext;
import com.chetan.service.services.OrganisationService;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/org")
public class OrganisationController {

    private final OrganisationService organisationService;

    @GetMapping(value = "/{orgId}", produces = {ContentType.PRODUCTS_1_0})
    public ResponseEntity<OrganisationValue> getOrganisation(@PathVariable("orgId") String orgId) {
        try {
            OrganisationValue organisation = organisationService.getOrganisation(TenantContext.getTenantId(), orgId);
            return new ResponseEntity<>(organisation, HttpStatus.OK);
        } catch (NotFoundException e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Void> createOrganisation(@Valid @RequestBody OrganisationValue organisation) throws JsonProcessingException, InterruptedException {
        organisationService.createOrganisation(organisation);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping(value = "/{orgId}",
            consumes = {ContentType.PRODUCT_1_0},
            produces = {ContentType.PRODUCT_1_0})
    public ResponseEntity<Void> addUserToOrganisation(@PathVariable("orgId") String orgId, @Valid @RequestBody UserValue user) {
        organisationService.addUserToOrganisation(TenantContext.getTenantId(), orgId, user);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
