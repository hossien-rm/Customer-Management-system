package com.cms.mapper;

import com.cms.dto.FactorDTO;
import com.cms.entity.Factor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


import java.util.List;

@Mapper
public interface FactorMapper {

    @Mappings({
            @Mapping(source = "customer.user.username", target = "customerName"),
            @Mapping(source = "server.id", target = "serverId")
    })
    FactorDTO entityToDto(Factor factor);

    List<FactorDTO> listEntityToDto(List<Factor> factors);

    Factor dtoToEntity(FactorDTO dto);

}
