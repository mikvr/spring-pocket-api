package com.rnd.corp.springpocketapi.service.dto.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsersExposedDTO {

    private String login;
    private String name;
    private String mail;
}
