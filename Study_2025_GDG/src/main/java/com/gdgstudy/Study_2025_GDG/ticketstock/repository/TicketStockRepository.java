package com.gdgstudy.Study_2025_GDG.ticketstock.repository;

import com.gdgstudy.Study_2025_GDG.ticket.Ticket;
import com.gdgstudy.Study_2025_GDG.ticketstock.TicketStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketStockRepository extends JpaRepository<TicketStock, Long> {
    Optional<TicketStock> findByTicket(Ticket ticket);
}
