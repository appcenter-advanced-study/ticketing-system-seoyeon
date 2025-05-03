package com.gdgstudy.Study_2025_GDG.reservation.service;

import com.gdgstudy.Study_2025_GDG.exception.NotFoundTicketException;
import com.gdgstudy.Study_2025_GDG.reservation.Reservation;
import com.gdgstudy.Study_2025_GDG.reservation.dto.CreateReservationRequest;
import com.gdgstudy.Study_2025_GDG.reservation.dto.CreateReservationResponse;
import com.gdgstudy.Study_2025_GDG.reservation.repository.ReservationRepository;
import com.gdgstudy.Study_2025_GDG.ticket.Ticket;
import com.gdgstudy.Study_2025_GDG.ticket.repository.TicketRepository;
import com.gdgstudy.Study_2025_GDG.ticketstock.service.TicketStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final TicketRepository ticketRepository;
    private final TicketStockService ticketStockService;
    public CreateReservationResponse createReservation(CreateReservationRequest request){
        Ticket ticket = ticketRepository.findById(request.getTicket_id()).orElseThrow(()-> new NotFoundTicketException("티켓을 찾을 수 없습니다."));
        Integer ticketStock = ticketStockService.subicketStock(ticket);
        Reservation reservation = request.toEntity(ticket);
        reservation = reservationRepository.save(reservation);
        return reservation.toDto(ticketStock);
    }
}
