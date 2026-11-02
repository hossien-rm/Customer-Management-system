package com.cms.service.impl;

import com.cms.constant.TicketStatus;
import com.cms.dto.TicketDTO;
import com.cms.entity.Ticket;
import com.cms.exception.CustomExceptionHandler;
import com.cms.mapper.TicketMapper;
import com.cms.repository.TicketRepository;
import com.cms.service.CustomerService;
import com.cms.service.TicketService;
import com.cms.service.UsersService;
import com.cms.util.CustomerUtils;
import com.cms.util.UserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;
    private final CustomerService customerService;
    private final UsersService usersService;
    private final AuditLogServiceImpl auditLogService;


    @Override
    public Ticket create(TicketDTO dto) {

        Ticket tempTicket = ticketMapper.dtoToEntity(dto);

        tempTicket.setStatus(TicketStatus.OPEN);
        tempTicket.setCustomer(customerService.findCustomerByUser(CustomerUtils.getCurrentCustomer()));

        Ticket ticket = ticketRepository.save(tempTicket);
        auditLogService.log(Ticket.class.getSimpleName(), ticket.getId(), "CREATE", ticket.getCreatedBy());
        return ticket;
    }

    @Override
    public Ticket replyToTicket(Long TicketId, TicketDTO dto) {

        Ticket tempTicket = ticketRepository.findById(TicketId)
                .orElseThrow(() -> new CustomExceptionHandler("Your ticket id for reply is not valid for reply by user."));

        tempTicket.setUser(usersService.findByUsername(UserUtils.getCurrentUsername()));
        tempTicket.setReply(dto.getReply());
        tempTicket.setStatus(TicketStatus.CLOSE);

        Ticket ticket = ticketRepository.save(tempTicket);

        auditLogService.log(Ticket.class.getSimpleName(), ticket.getId(), "UPDATE", ticket.getUpdatedBy());
        return ticket;
    }

    @Override
    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket findById(Long theId) {
        return ticketRepository.findById(theId)
                .orElseThrow(() -> new CustomExceptionHandler("Your ticket id is not valid"));
    }

    @Override
    public Ticket findByCustomer(Long customerId) {
        return null;
    }


    @Override
    public void deleteById(Long theId) {
        Ticket deleteTicket = ticketRepository.findById(theId)
                .orElseThrow(() -> new CustomExceptionHandler("Your ticket id is not valid for delete"));
        ticketRepository.delete(deleteTicket);
        auditLogService.log("Ticket", deleteTicket.getId(), "DELETE", deleteTicket.getUpdatedBy());
    }
}
