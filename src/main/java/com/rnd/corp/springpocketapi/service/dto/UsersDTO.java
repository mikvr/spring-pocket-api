package com.rnd.corp.springpocketapi.service.dto;

import java.util.Set;

import com.rnd.corp.springpocketapi.domain.ERole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsersDTO {

    private String login;
    private String name;
    private String password;
    private String mail;
    private String img;
    private Set<ERole> roles;

}
