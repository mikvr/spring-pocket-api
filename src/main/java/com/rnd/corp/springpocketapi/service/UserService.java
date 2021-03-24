package com.rnd.corp.springpocketapi.service;

import com.rnd.corp.springpocketapi.domain.Users;
import com.rnd.corp.springpocketapi.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {this.userRepository = userRepository;}

    public Users getUsers(final String login) {
        System.out.println(login);
        return this.userRepository.getUsersByLogin(login);
    }
}
