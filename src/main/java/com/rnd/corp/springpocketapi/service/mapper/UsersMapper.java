package com.rnd.corp.springpocketapi.service.mapper;

import com.rnd.corp.springpocketapi.domain.users.Users;
import com.rnd.corp.springpocketapi.service.dto.users.UsersDTO;
import com.rnd.corp.springpocketapi.service.dto.users.UsersExposedDTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UsersMapper {

    UsersExposedDTO toExposedDTO(Users users);

    @Mapping(target = "roles", ignore = true)
    Users toEntity(UsersDTO usersDTO);

}
