package com.gdgstudy.Study_2025_GDG.reservation.service;

import com.gdgstudy.Study_2025_GDG.exception.NotFoundTicketException;
import com.gdgstudy.Study_2025_GDG.reservation.Reservation;
import com.gdgstudy.Study_2025_GDG.reservation.dto.CreateReservationRequest;
import com.gdgstudy.Study_2025_GDG.reservation.dto.CreateReservationResponse;
import com.gdgstudy.Study_2025_GDG.reservation.repository.ReservationRepository;
import com.gdgstudy.Study_2025_GDG.ticket.Ticket;
import com.gdgstudy.Study_2025_GDG.ticket.repository.TicketRepository;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final TicketRepository ticketRepository;
    public ReservationService(ReservationRepository reservationRepository, TicketRepository ticketRepository){
        this.reservationRepository=reservationRepository;
        this.ticketRepository=ticketRepository;
    }

    public CreateReservationResponse createReservation(CreateReservationRequest request){
        Ticket ticket = ticketRepository.findById(request.getTicket_id()).orElseThrow(()-> new NotFoundTicketException("티켓을 찾을 수 없습니다."));
        Reservation reservation = request.toEntity(ticket);
        reservation = reservationRepository.save(reservation);
        return reservation.toDto();
    }
}
