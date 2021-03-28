package com.rnd.corp.springpocketapi.domain;

import java.util.HashSet;
import java.util.Set;
import javax.naming.AuthenticationException;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

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
@Table(name = "users", uniqueConstraints = {
    @UniqueConstraint(columnNames = "login"),
    @UniqueConstraint(columnNames = "mail")
})
public class Users {

    @Id
    private String login;
    private String name;
    private String password;
    private String mail;
    private String img;
    private boolean connected = Boolean.FALSE;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "users_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public void update(String name, String mail, String img) {
        this.name = name;
        this.mail = mail;
        this.img = img;
    }

}
