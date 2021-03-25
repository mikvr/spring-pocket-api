package com.rnd.corp.springpocketapi.domain;

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

    public void update(String name, String mail, String img) {
        this.name = name;
        this.mail = mail;
        this.img = img;
    }

    public void updatePwd(String pwd) {
        this.password = pwd;
    }
}
