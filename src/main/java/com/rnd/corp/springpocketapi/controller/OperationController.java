package com.rnd.corp.springpocketapi.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rnd.corp.springpocketapi.service.OperationService;
import com.rnd.corp.springpocketapi.service.dto.users.UsersDTO;
import com.rnd.corp.springpocketapi.service.dto.users.UsersLoginDTO;
import com.rnd.corp.springpocketapi.service.dto.users.UsersPwdDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class OperationController {

    private final OperationService operationService;

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody UsersLoginDTO usersLoginDTO, HttpServletRequest request,
        HttpServletResponse response) {
        return this.operationService.login(usersLoginDTO, request, response);
    }

    @GetMapping("/logout")
    public ResponseEntity<Void> logout(@RequestParam("login") String login) {
        return this.operationService.logout(login);
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(@RequestBody UsersDTO usersDTO) {
        return this.operationService.signUp(usersDTO);
    }

    @PutMapping("/pwd")
    public ResponseEntity<Void> updateUserPassword(@RequestBody UsersPwdDTO usersPwdDTO) {
        return this.operationService.updatePwd(usersPwdDTO);
    }

}
