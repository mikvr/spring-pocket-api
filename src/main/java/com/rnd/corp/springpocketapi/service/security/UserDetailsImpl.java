package com.rnd.corp.springpocketapi.service.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rnd.corp.springpocketapi.domain.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private static final long serialVersionUID = 1L;
    private String login;
    private String mail;
    private String name;
    private String img;
    @JsonIgnore
    private String password;
    private Collection<? extends GrantedAuthority> authorities;


    public static UserDetailsImpl build(Users users) {
        List<GrantedAuthority> authorities = users.getRoles()
                                                  .stream()
                                                  .map(role -> new SimpleGrantedAuthority(role.getRole().name()))
                                                  .collect(Collectors.toList());
        return new UserDetailsImpl(
            users.getLogin(),
            users.getMail(),
            users.getName(),
            users.getImg(),
            users.getPassword(),
            authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return Boolean.TRUE;
    }

    @Override
    public boolean isAccountNonLocked() {
        return Boolean.TRUE;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return Boolean.TRUE;
    }

    @Override
    public boolean isEnabled() {
        return Boolean.TRUE;
    }
}
