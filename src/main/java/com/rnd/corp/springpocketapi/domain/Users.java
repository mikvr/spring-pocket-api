package com.rnd.corp.springpocketapi.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class Users {

    @Id
    private String login;
    private String name;
    private String mail;
    private String img;
}
