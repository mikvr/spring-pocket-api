package com.rnd.corp.springpocketapi.repository;

import java.util.Optional;

import com.rnd.corp.springpocketapi.domain.ERole;
import com.rnd.corp.springpocketapi.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRole(ERole role);
}
