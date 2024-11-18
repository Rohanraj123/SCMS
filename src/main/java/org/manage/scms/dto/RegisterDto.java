package org.manage.scms.dto;

import lombok.Getter;
import lombok.Setter;
import org.manage.scms.constant.Role;

import java.util.Set;


@Getter
@Setter
public class RegisterDto {

    private String username;
    private String password;

    private String email;
    private Set<Role> roles;
}
