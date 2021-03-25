package com.rnd.corp.springpocketapi.domain;

import javax.naming.AuthenticationException;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class Users {

    @Id
    private String login;
    private String name;
    private String password;
    private String mail;
    private String img;
    private boolean connected;

    public void update(String name, String mail, String img) {
        this.name = name;
        this.mail = mail;
        this.img = img;
    }

    public void updatePwd(String pwd) {
        this.password = pwd;
    }

    public void authenticate(String pwd) throws AuthenticationException {
        if (!this.password.equals(pwd)) {
            throw new AuthenticationException("Wrong Password");
        }
        this.connected = Boolean.TRUE;
    }
}
