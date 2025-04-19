package com.gdgstudy.Study_2025_GDG.ticket.service;

import com.gdgstudy.Study_2025_GDG.ticket.Ticket;
import com.gdgstudy.Study_2025_GDG.ticket.dto.CreateTicketRequest;
import com.gdgstudy.Study_2025_GDG.ticket.dto.CreateTicketResponse;
import com.gdgstudy.Study_2025_GDG.ticket.repository.TicketRepository;
import com.gdgstudy.Study_2025_GDG.ticketstock.service.TicketStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final TicketStockService ticketStockService;

    public CreateTicketResponse createTicket(CreateTicketRequest request){
        Ticket ticket = request.toEntity();
        ticket = ticketRepository.save(ticket);
        Integer ticketStock = ticketStockService.createTiketStock(ticket);
        return ticket.toDto(ticketStock);
    }
}
