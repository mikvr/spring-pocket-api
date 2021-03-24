package com.rnd.corp.springpocketapi.controller;

import com.rnd.corp.springpocketapi.domain.Users;
import com.rnd.corp.springpocketapi.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {this.userService = userService;}

    @GetMapping("/{login}")
    Users getUsers(@PathVariable("login") String login) {
        return this.userService.getUsers(login);
    }
}
