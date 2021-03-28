package com.rnd.corp.springpocketapi.service.dto.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsersPwdDTO {

    private String login;
    private String old;
    private String pwd;
}
