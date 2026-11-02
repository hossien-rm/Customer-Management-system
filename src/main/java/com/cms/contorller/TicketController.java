package com.cms.contorller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.cms.dto.TicketDTO;
import com.cms.mapper.TicketMapper;
import com.cms.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Tickets", description = "Create support tickets, reply to them and manage tickets.")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;
    private final TicketMapper ticketMapper;


    @Operation(summary = "Create a ticket", description = "Creates a support ticket. Requires CUSTOMER.")
    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping("/ticket")
    public TicketDTO createTicket(@RequestBody TicketDTO dto) {
        return ticketMapper.entityToDto(ticketService.create(dto));
    }

    @Operation(summary = "Reply to a ticket", description = "Adds a reply to a ticket by ID. Requires ADMIN.")
    @PreAuthorize("hasRole('SUPPORT')")
    @PutMapping("/ticket/{theId}/reply")
    public TicketDTO replyToTicketWithUser(@PathVariable Long theId, @RequestBody TicketDTO dto) {
        return ticketMapper.entityToDto(ticketService.replyToTicket(theId, dto));
    }

    @Operation(summary = "List tickets", description = "Returns all support tickets. Requires SUPPORT.")
    @PreAuthorize("hasRole('SUPPORT')")
    @GetMapping("/ticket")
    public List<TicketDTO> findAllTicket() {
        return ticketMapper.ListEntityToDto(ticketService.findAll());
    }

    @Operation(summary = "Delete a ticket", description = "Deletes a support ticket by ID. Requires ADMIN.")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/ticket/{theId}")
    public void deleteTicketById(@PathVariable Long theId) {
        ticketService.deleteById(theId);
    }

}
