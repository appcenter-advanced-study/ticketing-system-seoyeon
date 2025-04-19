package com.gdgstudy.Study_2025_GDG.ticket.repository;

import com.gdgstudy.Study_2025_GDG.ticket.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
