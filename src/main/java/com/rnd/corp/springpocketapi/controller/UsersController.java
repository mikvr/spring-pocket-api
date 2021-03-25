package com.rnd.corp.springpocketapi.controller;

import com.rnd.corp.springpocketapi.service.UsersService;
import com.rnd.corp.springpocketapi.service.dto.UsersUpdateDTO;
import com.rnd.corp.springpocketapi.service.dto.UsersDTO;
import com.rnd.corp.springpocketapi.service.dto.UsersExposedDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    public ResponseEntity<UsersExposedDTO> getUserByLogin(@PathVariable("login") String login) {
        return this.usersService.getUsersByLogin(login);
    }

    @PostMapping("")
    public ResponseEntity<UsersExposedDTO> createUser(@RequestBody UsersDTO usersDTO) {
        return this.usersService.saveUsers(usersDTO);
    }

    @PutMapping("/{login}")
    public ResponseEntity<Void> updateUser(@RequestBody UsersUpdateDTO usersUpdateDTO, @PathVariable("login") String login) {
        return this.usersService.updateUsers(login, usersUpdateDTO);
    }

    @DeleteMapping("/{login}")
    public ResponseEntity<Void> deleteUser(@PathVariable("login") String login) {
        return this.usersService.deleteUsers(login);
    }
}
