package com.gdgstudy.Study_2025_GDG.ticketstock.repository;

import com.gdgstudy.Study_2025_GDG.ticketstock.TicketStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketStockRepository extends JpaRepository<TicketStock, Long> {
}
