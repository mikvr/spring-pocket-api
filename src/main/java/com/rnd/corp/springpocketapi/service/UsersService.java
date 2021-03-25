package com.rnd.corp.springpocketapi.service;

import com.rnd.corp.springpocketapi.domain.Users;
import com.rnd.corp.springpocketapi.repository.UsersRepository;
import com.rnd.corp.springpocketapi.service.dto.UsersPwdDTO;
import com.rnd.corp.springpocketapi.service.dto.UsersUpdateDTO;
import com.rnd.corp.springpocketapi.service.dto.UsersDTO;
import com.rnd.corp.springpocketapi.service.dto.UsersExposedDTO;
import com.rnd.corp.springpocketapi.service.mapper.UsersMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UsersService {

    private final UsersRepository usersRepository;
    private final UsersMapper usersMapper;

    public ResponseEntity<UsersExposedDTO> getUsersByLogin(final String login) {
        if(!checkLoginAvailability(login)) {
            final Users users = this.usersRepository.getUsersByLogin(login);
            return ResponseEntity.ok(this.usersMapper.toExposedDTO(users));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    public ResponseEntity<UsersExposedDTO> saveUsers(final UsersDTO usersDTO) {
        if (checkLoginAvailability(usersDTO.getLogin())) {
            Users users = this.usersRepository.save(this.usersMapper.toEntity(usersDTO));
            return ResponseEntity.ok(this.usersMapper.toExposedDTO(users));
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    public ResponseEntity<Void> updateUsers(final String login, final UsersUpdateDTO usersUpdateDTO) {
        if(!checkLoginAvailability(login)) {
            Users users = this.usersRepository.getUsersByLogin(login);
            users.update(usersUpdateDTO.getName(), usersUpdateDTO.getMail(), usersUpdateDTO.getImg());
            this.usersRepository.save(users);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    public ResponseEntity<Void> deleteUsers(final String login) {
        if (!checkLoginAvailability(login)) {
            final Users users = this.usersRepository.getUsersByLogin(login);
            this.usersRepository.delete(users);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    public ResponseEntity<Void> updatePwd(final String login, final UsersPwdDTO usersPwdDTO) {
        if (!checkLoginAvailability(login)) {
            final Users users = this.usersRepository.getUsersByLogin(login);
            users.updatePwd(usersPwdDTO.getPassword());
            this.usersRepository.save(users);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
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
}
