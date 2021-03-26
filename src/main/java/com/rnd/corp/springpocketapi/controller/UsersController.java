package com.rnd.corp.springpocketapi.controller;

import com.rnd.corp.springpocketapi.service.UsersService;
import com.rnd.corp.springpocketapi.service.dto.UsersExposedDTO;
import com.rnd.corp.springpocketapi.service.dto.UsersPwdDTO;
import com.rnd.corp.springpocketapi.service.dto.UsersUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UsersController {

    private final UsersService usersService;

    @GetMapping("/{login}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UsersExposedDTO> getUserByLogin(@PathVariable("login") String login) {
        return this.usersService.getUsersByLogin(login);
    }

    @PutMapping("/{login}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> updateUser(@RequestBody UsersUpdateDTO usersUpdateDTO, @PathVariable("login") String login) {
        return this.usersService.updateUsers(login, usersUpdateDTO);
    }

    @DeleteMapping("/{login}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<Void> deleteUser(@PathVariable("login") String login) {
        return this.usersService.deleteUsers(login);
    }

    @PutMapping("/{login}/pwd")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> updateUserPassword(@RequestBody UsersPwdDTO usersPwdDTO, @PathVariable("login") String login) {
        return this.usersService.updatePwd(login, usersPwdDTO);
    }
}
