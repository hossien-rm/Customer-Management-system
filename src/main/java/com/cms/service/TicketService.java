package com.cms.service;

import com.cms.dto.TicketDTO;
import com.cms.entity.Ticket;

import java.util.List;

public interface TicketService {

    Ticket create(TicketDTO dto);

    Ticket replyToTicket(Long TicketId, TicketDTO dto);

    List<Ticket> findAll();

    Ticket findById(Long theId);

    Ticket findByCustomer(Long customerId);

    void deleteById(Long theId);

}
