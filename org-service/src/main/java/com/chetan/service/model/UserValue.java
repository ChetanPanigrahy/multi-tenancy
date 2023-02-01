package com.chetan.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.chetan.service.domain.entity.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserValue {
    @NotNull
    private String firstName;
    private String lastName;
    @NotNull
    @Email
    private String email;
    @NotNull
    private String phone;
    private List<RoleValue> roles;

    public static UserValue fromEntity(User user) {
        return UserValue.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .roles(RoleValue.fromEntity(user.getRoles()))
                .build();
    }
}
