package com.rnd.corp.springpocketapi.service;

import javax.transaction.Transactional;

import com.rnd.corp.springpocketapi.domain.Users;
import com.rnd.corp.springpocketapi.exception.ConflictException;
import com.rnd.corp.springpocketapi.exception.ResourceNotFoundException;
import com.rnd.corp.springpocketapi.exception.UnauthorizedExceptionHandler;
import com.rnd.corp.springpocketapi.repository.UsersRepository;
import com.rnd.corp.springpocketapi.service.dto.UsersDTO;
import com.rnd.corp.springpocketapi.service.dto.UsersExposedDTO;
import com.rnd.corp.springpocketapi.service.dto.UsersPwdDTO;
import com.rnd.corp.springpocketapi.service.dto.UsersUpdateDTO;
import com.rnd.corp.springpocketapi.service.mapper.UsersMapper;
import com.rnd.corp.springpocketapi.service.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UsersService implements UserDetailsService {

    private final UsersRepository usersRepository;
    private final UsersMapper usersMapper;

    public ResponseEntity<UsersExposedDTO> getUsersByLogin(final String login) {
        if (!checkLoginAvailability(login)) {
            final Users users = this.usersRepository.getUsersByLogin(login);
            return ResponseEntity.ok(this.usersMapper.toExposedDTO(users));
        }
        throw new ResourceNotFoundException();
    }

    public ResponseEntity<Void> updateUsers(final String login, final UsersUpdateDTO usersUpdateDTO) {
        final UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(user.getPassword());
        System.out.println(user.getName());
        if (user.getLogin().equals(login)) {
            Users users = this.usersRepository.getUsersByLogin(login);
            System.out.println(users.getPassword());
            System.out.println(users.getName());
            users.update(usersUpdateDTO.getName(), usersUpdateDTO.getMail(), usersUpdateDTO.getImg());

            this.usersRepository.save(users);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        throw new UnauthorizedExceptionHandler();
        /*if(!checkLoginAvailability(login)) {
            Users users = this.usersRepository.getUsersByLogin(login);
            users.update(usersUpdateDTO.getName(), usersUpdateDTO.getMail(), usersUpdateDTO.getImg());
            this.usersRepository.save(users);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        throw new ResourceNotFoundException();*/
    }

    public ResponseEntity<Void> deleteUsers(final String login) {
        if (!checkLoginAvailability(login)) {
            final Users users = this.usersRepository.getUsersByLogin(login);
            this.usersRepository.delete(users);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        throw new ResourceNotFoundException();
    }

    public ResponseEntity<Void> updatePwd(final String login, final UsersPwdDTO usersPwdDTO) {
        if (!checkLoginAvailability(login)) {
            final Users users = this.usersRepository.getUsersByLogin(login);
            users.updatePwd(usersPwdDTO.getPassword());
            this.usersRepository.save(users);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        throw new ResourceNotFoundException();
    }

    /**
     * retourne vrai si le login est disponible
     *
     * @param login login
     * @return booleen
     */
    private boolean checkLoginAvailability(final String login) {
        return this.usersRepository.getUsersByLogin(login) == null;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        final Users user = usersRepository.getUsersByLogin(login);
        return UserDetailsImpl.build(user);
    }
}
