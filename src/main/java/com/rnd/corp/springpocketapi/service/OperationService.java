package com.rnd.corp.springpocketapi.service;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.rnd.corp.springpocketapi.domain.Users;
import com.rnd.corp.springpocketapi.exception.OperationException;
import com.rnd.corp.springpocketapi.exception.ResourceNotFoundException;
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
                throw new OperationException(e.getMessage(), HttpStatus.BAD_REQUEST);
            }

            try {
                String token = JwtHelper.auth(users.getLogin(), request.getHeader("Origin"));
                response.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } catch (JWTCreationException exc) {
                throw new OperationException(exc.getMessage(), HttpStatus.UNAUTHORIZED);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    public ResponseEntity<Void> logout(final String login) {
        final Users users = this.usersRepository.getUsersByLogin(login);
        if (users != null) {
            users.disconnect();
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        throw new ResourceNotFoundException();
    }

}
