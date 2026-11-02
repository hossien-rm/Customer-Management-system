package com.cms.mapper;

import com.cms.dto.UsersDTO;
import com.cms.entity.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface UsersMapper {

    @Mapping(target = "password", ignore = true)
    UsersDTO entityToDto(Users user);

    List<UsersDTO> ListEntityToDto(List<Users> user);

    Users dtoToEntity(UsersDTO dto);

}
