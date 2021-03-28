package com.rnd.corp.springpocketapi.service.dto.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsersLoginDTO {

    private String login;
    private String password;
}
