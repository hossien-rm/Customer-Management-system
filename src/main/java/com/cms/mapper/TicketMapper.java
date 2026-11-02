package com.cms.mapper;

import com.cms.dto.TicketDTO;
import com.cms.entity.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface TicketMapper {


    @Mapping(source = "customer.user.username", target = "customerName")
    @Mapping(source = "user.username", target = "supportName")
    TicketDTO entityToDto(Ticket ticket);

    List<TicketDTO> ListEntityToDto(List<Ticket> tickets);

    @Mapping(target = "reply", ignore = true)
    Ticket dtoToEntity(TicketDTO dto);

}
