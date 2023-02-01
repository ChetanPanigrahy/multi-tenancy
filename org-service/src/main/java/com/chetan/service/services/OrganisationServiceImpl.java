package com.chetan.service.services;

import com.chetan.service.client.KafkaProducer;
import com.chetan.service.controller.NotFoundException;
import com.chetan.service.domain.entity.Organisation;
import com.chetan.service.domain.entity.Role;
import com.chetan.service.domain.entity.User;
import com.chetan.service.model.OrganisationValue;
import com.chetan.service.model.TenantValue;
import com.chetan.service.model.TenantValue2;
import com.chetan.service.model.UserValue;
import com.chetan.service.repository.OrganisationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OrganisationServiceImpl implements OrganisationService {
    @Autowired
    private final OrganisationRepository organisationRepository;

    @Autowired
    private final KafkaProducer kafkaProducer;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Override
    public OrganisationValue getOrganisation(String tenantId, String orgId) {
        return organisationRepository.findByTenantIdAndOrgId(tenantId, orgId)
                .map(OrganisationValue::fromEntity)
                .orElseThrow(() -> new NotFoundException("OrgId " + orgId + " not found"));
    }

    @Override
    public void createOrganisation(OrganisationValue organisation) {
        String tenantId = ManagerUtils.generateId("TNT").toLowerCase();
        String orgId = ManagerUtils.generateId("ORG");
        ArrayList<Role> roles = new ArrayList<>();
        organisation.getUsers().forEach( u ->
                roles.addAll(u.getRoles())
        );
        Organisation orgEntity = Organisation.builder()
                .orgId(orgId)
                .orgName(organisation.getOrgName())
                .users(organisation.getUsers())
                .tenantId(tenantId)
                .modules(organisation.getModules())
                .roles(roles)
                .build();

        organisationRepository.save(orgEntity);

        organisation.getModules().forEach(k -> kafkaProducer.sendTenantMessage(TenantValue.builder()
                .tenantId(tenantId)
                .schema(tenantId + "-" + k.getModuleName())
                .orgId(orgId)
                .module(k.getModuleName())
                .build()));
        kafkaProducer.sendTenantMessage2(new TenantValue2());
//        tenantManagementServiceClient.addNewTenant(tenant);

    }

    @Override
    public void addUserToOrganisation(String tenantId, String orgId, UserValue user) {
        Optional<Organisation> organisation = organisationRepository.findByTenantIdAndOrgId(tenantId, orgId);

        List<Role> roleList = new ArrayList<>();
        user.getRoles().forEach(role -> roleList.add(
                Role.builder().roleId(ManagerUtils.generateId("RLE")).roleType(role.getRoleType()).build()
        ));

        String userId = ManagerUtils.generateId("USR");
        User userEntity = User.builder()
                .userId(userId)
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .roles(roleList)
                .build();

        organisation.ifPresent(org -> {
            List<User> orgUsers = org.getUsers();
            orgUsers.add(userEntity);
            organisationRepository.save(org);
        });
    }
}
