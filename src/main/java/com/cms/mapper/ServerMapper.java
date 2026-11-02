package com.cms.mapper;

import com.cms.dto.ServerDTO;
import com.cms.entity.Server;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface ServerMapper {


    @Mapping(source = "customer.user.username", target = "customerName")
    ServerDTO entityToDto(Server server);

    List<ServerDTO> listEntityToDto(List<Server> servers);

    Server dtoToEntity(ServerDTO dto);


}
