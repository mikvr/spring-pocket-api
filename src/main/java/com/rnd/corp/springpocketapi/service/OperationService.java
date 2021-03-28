package com.rnd.corp.springpocketapi.service;

import java.util.HashSet;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rnd.corp.springpocketapi.domain.users.ERole;
import com.rnd.corp.springpocketapi.domain.users.Role;
import com.rnd.corp.springpocketapi.domain.users.Users;
import com.rnd.corp.springpocketapi.exception.BadRequestHandler;
import com.rnd.corp.springpocketapi.exception.ResourceNotFoundException;
import com.rnd.corp.springpocketapi.repository.RoleRepository;
import com.rnd.corp.springpocketapi.repository.UsersRepository;
import com.rnd.corp.springpocketapi.service.dto.users.UsersDTO;
import com.rnd.corp.springpocketapi.service.dto.users.UsersLoginDTO;
import com.rnd.corp.springpocketapi.service.dto.users.UsersPwdDTO;
import com.rnd.corp.springpocketapi.service.mapper.UsersMapper;
import com.rnd.corp.springpocketapi.utils.JwtHelper;
import com.rnd.corp.springpocketapi.utils.UsersServiceHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OperationService {

    private final UsersRepository usersRepository;
    private final RoleRepository roleRepository;

    private final AuthenticationManager authenticationManager;
    private final UsersMapper usersMapper;
    private final PasswordEncoder encoder;

    /**
     * User's login service
     * @param usersLoginDTO login && pwd
     * @param request request
     * @param response response
     * @return response status
     */
    public ResponseEntity<Void> login(final UsersLoginDTO usersLoginDTO, HttpServletRequest request,
        HttpServletResponse response) {

        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(usersLoginDTO.getLogin(), usersLoginDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = JwtHelper.auth(authentication.getName(), request.getHeader("Origin"));
        response.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
        final Users connectedUser = this.usersRepository.getUsersByLogin(usersLoginDTO.getLogin());

        //Change user's Status
        connectedUser.setConnected(Boolean.TRUE);
        this.usersRepository.save(connectedUser);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * User's logout service
     * @param login user's login
     * @return Response Status
     */
    public ResponseEntity<Void> logout(final String login) {
        final Users users = this.usersRepository.getUsersByLogin(login);
        if (users != null) {
            users.setConnected(Boolean.FALSE);
            this.usersRepository.save(users);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        throw new ResourceNotFoundException();
    }

    /**
     * Add new User
     * @param usersDTO user to add
     * @return Response status
     */
    public ResponseEntity<Void> signUp(UsersDTO usersDTO) {
        if (this.usersRepository.existsByLogin(usersDTO.getLogin())) {
            throw new BadRequestHandler("Error: Username is already taken!");
        }

        if (this.usersRepository.existsByMail(usersDTO.getMail())) {
            throw new BadRequestHandler("Error: e-mail is already in use!");
        }

        final Users user = this.usersMapper.toEntity(usersDTO);
        user.setPassword(encoder.encode(usersDTO.getPassword()));
        final Set<ERole> strRoles = usersDTO.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles.isEmpty()) {
            Role userRole = this.roleRepository
                .findByRole(ERole.ROLE_USER)
                .orElseThrow(ResourceNotFoundException::new);
            roles.add(userRole);
        } else {
            // Use a switch for later : may add multiple roles
            strRoles.forEach(role -> {
                switch (role) {
                case ROLE_ADMIN:
                    Role adminRole = this.roleRepository
                        .findByRole(ERole.ROLE_ADMIN)
                        .orElseThrow(ResourceNotFoundException::new);
                    roles.add(adminRole);
                    break;
                default:
                    Role userRole = this.roleRepository
                        .findByRole(ERole.ROLE_USER)
                        .orElseThrow(ResourceNotFoundException::new);
                    roles.add(userRole);
                    break;
                }
            });
        }
        user.setRoles(roles);
        user.setConnected(Boolean.TRUE);
        this.usersRepository.save(user);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * Update user's password
     *
     * @param usersPwdDTO a representation of the new password and the actual password
     * @return Response status
     */
    public ResponseEntity<Void> updatePwd(final UsersPwdDTO usersPwdDTO) {
        if (this.usersRepository.existsByLogin(usersPwdDTO.getLogin())
            && UsersServiceHelper.checkUserOrigin(usersPwdDTO.getLogin())) {

            try {
                authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(usersPwdDTO.getLogin(), usersPwdDTO.getOld()));
                Users users = this.usersRepository.getUsersByLogin(usersPwdDTO.getLogin());
                users.setPassword(encoder.encode(usersPwdDTO.getPwd()));
                this.usersRepository.save(users);
                return ResponseEntity.status(HttpStatus.OK).build();
            } catch (AuthenticationException e) {
                throw new BadRequestHandler(
                    "Wrong password : The actual password is not the same the password mentionned");
            }
        }
        throw new ResourceNotFoundException();
    }

}
