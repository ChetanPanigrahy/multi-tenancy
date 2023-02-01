package com.chetan.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.chetan.service.domain.entity.Role;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleValue {
    private String roleType;

    public static RoleValue fromEntity(Role role) {
        return RoleValue.builder()
                .roleType(role.getRoleType())
                .build();
    }

    public static List<RoleValue> fromEntity(List<Role> roles) {
        List<RoleValue> roleList = new ArrayList<>();

        roles.forEach(role -> roleList.add(RoleValue.fromEntity(role)));

        return roleList;
    }
}
