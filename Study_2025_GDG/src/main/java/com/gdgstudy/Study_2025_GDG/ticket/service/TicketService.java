package com.gdgstudy.Study_2025_GDG.ticket.service;

import com.gdgstudy.Study_2025_GDG.ticket.Ticket;
import com.gdgstudy.Study_2025_GDG.ticket.dto.CreateTicketRequest;
import com.gdgstudy.Study_2025_GDG.ticket.dto.CreateTicketResponse;
import com.gdgstudy.Study_2025_GDG.ticket.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    public TicketService(TicketRepository ticketRepository){this.ticketRepository=ticketRepository;}

    public CreateTicketResponse createTicket(CreateTicketRequest request){
        Ticket ticket = request.toEntity();
        ticket = ticketRepository.save(ticket);
        return ticket.toDto();
    }
}
