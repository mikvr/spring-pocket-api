package com.rnd.corp.springpocketapi.service;

import javax.transaction.Transactional;

import com.rnd.corp.springpocketapi.domain.Users;
import com.rnd.corp.springpocketapi.exception.BadRequestHandler;
import com.rnd.corp.springpocketapi.exception.ResourceNotFoundException;
import com.rnd.corp.springpocketapi.exception.UnauthorizedExceptionHandler;
import com.rnd.corp.springpocketapi.repository.UsersRepository;
import com.rnd.corp.springpocketapi.service.dto.UsersExposedDTO;
import com.rnd.corp.springpocketapi.service.dto.UsersUpdateDTO;
import com.rnd.corp.springpocketapi.service.mapper.UsersMapper;
import com.rnd.corp.springpocketapi.service.security.UserDetailsImpl;
import com.rnd.corp.springpocketapi.utils.UsersServiceHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UsersService implements UserDetailsService{

    private final UsersRepository usersRepository;
    private final UsersMapper usersMapper;

    /**
     * Send user's credentials
     *
     * @param login user's login
     * @return user's credentials
     */
    public ResponseEntity<UsersExposedDTO> getUsersByLogin(final String login) {
        if (this.usersRepository.existsByLogin(login)) {
            final Users users = this.usersRepository.getUsersByLogin(login);
            return ResponseEntity.ok(this.usersMapper.toExposedDTO(users));
        }
        throw new ResourceNotFoundException();
    }

    /**
     * Update user's credentials
     *
     * @param login          user's login
     * @param usersUpdateDTO update to apply
     * @return response entity
     */
    public ResponseEntity<Void> updateUsers(final String login, final UsersUpdateDTO usersUpdateDTO) {
        if (UsersServiceHelper.checkUserOrigin(login)) {
            Users users = this.usersRepository.getUsersByLogin(login);
            if (this.usersRepository.existsByMail(usersUpdateDTO.getMail())) {
                throw new BadRequestHandler("This mail (" + usersUpdateDTO.getMail() + ") is already in use");
            }
            users.update(usersUpdateDTO.getName(), usersUpdateDTO.getMail(), usersUpdateDTO.getImg());
            this.usersRepository.save(users);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        throw new UnauthorizedExceptionHandler();
    }

    /**
     * Delete an user within the database
     *
     * @param login user's login
     * @return Response Status
     */
    public ResponseEntity<Void> deleteUsers(final String login) {
        if (UsersServiceHelper.checkUserOrigin(login)) {
            final Users users = this.usersRepository.getUsersByLogin(login);
            this.usersRepository.delete(users);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        throw new UnauthorizedExceptionHandler();
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        final Users user = usersRepository.getUsersByLogin(login);
        return UserDetailsImpl.build(user);
    }
}
