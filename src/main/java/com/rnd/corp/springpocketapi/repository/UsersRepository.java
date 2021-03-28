package com.rnd.corp.springpocketapi.repository;

import com.rnd.corp.springpocketapi.domain.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    Users getUsersByLogin(String login);

    Boolean existsByLogin(String login);

    Boolean existsByMail(String mail);

}
