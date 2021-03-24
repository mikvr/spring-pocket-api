package com.rnd.corp.springpocketapi.repository;

import com.rnd.corp.springpocketapi.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {

    Users getUsersByLogin(String login);
}
