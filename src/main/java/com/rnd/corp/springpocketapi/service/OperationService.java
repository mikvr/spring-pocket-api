package com.rnd.corp.springpocketapi.service;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.rnd.corp.springpocketapi.domain.Users;
import com.rnd.corp.springpocketapi.repository.UsersRepository;
import com.rnd.corp.springpocketapi.service.dto.UsersPwdDTO;
import com.rnd.corp.springpocketapi.utils.JwtHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OperationService {

    private final UsersRepository usersRepository;

    public ResponseEntity<Void> login(final UsersPwdDTO usersPwdDTO, HttpServletRequest request,
        HttpServletResponse response) {

        final Users users = this.usersRepository.getUsersByLogin(usersPwdDTO.getLogin());
        if (users != null) {
            try {
                users.authenticate(usersPwdDTO.getPassword());
            } catch (AuthenticationException e) {
                e.printStackTrace();
            }

            try {
                String token = JwtHelper.auth(users.getLogin(), request.getHeader("Origin"));
                response.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } catch (JWTCreationException exc) {
                throw new JWTCreationException("Token generation failed !", exc);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
