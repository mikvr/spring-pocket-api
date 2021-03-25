package com.rnd.corp.springpocketapi.service;

import com.rnd.corp.springpocketapi.domain.Users;
import com.rnd.corp.springpocketapi.repository.UsersRepository;
import com.rnd.corp.springpocketapi.service.dto.UsersDTO;
import com.rnd.corp.springpocketapi.service.dto.UsersExposedDTO;
import com.rnd.corp.springpocketapi.service.mapper.UsersMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UsersService {

    private final UsersRepository usersRepository;
    private final UsersMapper usersMapper;

    public UsersExposedDTO getUsersByLogin(final String login) {
        final Users users = this.usersRepository.getUsersByLogin(login);
        return this.usersMapper.toExposedDTO(users);
    }

    public UsersExposedDTO saveUsers(final UsersDTO usersDTO) {
        Users users = this.usersRepository.save(this.usersMapper.toEntity(usersDTO));
        return this.usersMapper.toExposedDTO(users);
    }
}
