package com.chetan.service.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.chetan.service.model.OrganisationValue;
import com.chetan.service.model.UserValue;

public interface OrganisationService {
    OrganisationValue getOrganisation(String tenantId, String orgId);

    void createOrganisation(OrganisationValue organisation) throws JsonProcessingException, InterruptedException;

    void addUserToOrganisation(String tenantId, String orgId, UserValue user);
}
