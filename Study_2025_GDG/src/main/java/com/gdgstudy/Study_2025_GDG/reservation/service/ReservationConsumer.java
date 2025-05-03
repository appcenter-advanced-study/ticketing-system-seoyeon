package com.gdgstudy.Study_2025_GDG.reservation.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gdgstudy.Study_2025_GDG.exception.NotFoundTicketException;
import com.gdgstudy.Study_2025_GDG.exception.OutOfStockException;
import com.gdgstudy.Study_2025_GDG.reservation.dto.CreateReservationRequest;
import com.gdgstudy.Study_2025_GDG.reservation.repository.ReservationRepository;
import com.gdgstudy.Study_2025_GDG.ticket.Ticket;
import com.gdgstudy.Study_2025_GDG.ticket.repository.TicketRepository;
import com.gdgstudy.Study_2025_GDG.ticketstock.TicketStock;
import com.gdgstudy.Study_2025_GDG.ticketstock.repository.TicketStockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservationConsumer {
    private final ObjectMapper objectMapper;
    private final TicketRepository ticketRepository;
    private final TicketStockRepository ticketStockRepository;
    private final ReservationRepository reservationRepository;

    @KafkaListener(topics = "reservation.request", groupId = "reservation-group")
    public void consumeReservation(String message) {
        try {
            CreateReservationRequest request = objectMapper.readValue(message, CreateReservationRequest.class);
            Ticket ticket = ticketRepository.findById(request.getTicket_id())
                    .orElseThrow(() -> new NotFoundTicketException("티켓이 없습니다."));

            TicketStock ticketStock = ticketStockRepository.findByTicket(ticket)
                    .orElseThrow(() -> new NotFoundTicketException("티켓 재고가 없습니다."));

            ticketStock.subTicketStock();
            reservationRepository.save(request.toEntity(ticket));

        } catch (OutOfStockException e) {
            // 재고 부족 예약 처리하지 않음
            System.err.println("재고 부족: " + e.getMessage());

        } catch (Exception e) {
            // 그 외 예외 처리
            System.err.println("Kafka Consumer 처리 오류: " + e.getMessage());
        }
    }
}
