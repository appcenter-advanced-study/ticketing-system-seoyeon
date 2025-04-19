package com.gdgstudy.Study_2025_GDG.ticketstock.service;

import com.gdgstudy.Study_2025_GDG.exception.NotFoundTicketException;
import com.gdgstudy.Study_2025_GDG.ticket.Ticket;
import com.gdgstudy.Study_2025_GDG.ticketstock.TicketStock;
import com.gdgstudy.Study_2025_GDG.ticketstock.repository.TicketStockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketStockService {
    private final TicketStockRepository ticketStockRepository;

    public Integer createTiketStock(Ticket ticket){
        TicketStock ticketStock = ticketStockRepository.findByTicket(ticket).orElse(TicketStock.builder().ticket(ticket).build());
        ticketStock = ticketStockRepository.save(ticketStock);
        return ticketStock.getQuantity();
    }
    public Integer addTicketStock(Ticket ticket){
        TicketStock ticketStock = ticketStockRepository.findByTicket(ticket).orElseThrow(()->new NotFoundTicketException("티켓스톡이 없습니다."));
        return ticketStock.addTicketStock();
    }
    public Integer subicketStock(Ticket ticket){
        TicketStock ticketStock = ticketStockRepository.findByTicket(ticket).orElseThrow(()->new NotFoundTicketException("티켓스톡이 없습니다."));
        return ticketStock.subTicketStock();
    }

}
