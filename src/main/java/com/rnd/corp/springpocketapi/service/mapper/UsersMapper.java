package com.rnd.corp.springpocketapi.service.mapper;

import com.rnd.corp.springpocketapi.domain.Users;
import com.rnd.corp.springpocketapi.service.dto.UsersDTO;
import com.rnd.corp.springpocketapi.service.dto.UsersExposedDTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UsersMapper {

    UsersDTO toDTO(Users users);
    UsersExposedDTO toExposedDTO(Users users);
    Users toEntity(UsersDTO usersDTO);
}
