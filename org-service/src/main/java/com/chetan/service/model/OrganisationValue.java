package com.chetan.service.model;

import com.chetan.service.domain.entity.Module;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.chetan.service.domain.entity.Organisation;
import com.chetan.service.domain.entity.User;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrganisationValue {
    @NotNull
    private String orgName;
    private List<User> users;
    private List<Module> modules;

    public static OrganisationValue fromEntity(Organisation organisation) {
        return OrganisationValue.builder()
                .orgName(organisation.getOrgName())
                .users(organisation.getUsers())
                .modules(organisation.getModules())
                .build();
    }
}
