package com.gdgstudy.Study_2025_GDG.reservation.dto;

import com.gdgstudy.Study_2025_GDG.reservation.Reservation;
import com.gdgstudy.Study_2025_GDG.ticket.Ticket;
import lombok.Getter;

@Getter
public class CreateReservationRequest {
    private Long ticket_id;
    private String user_name;

    public Reservation toEntity(Ticket ticket){
        return Reservation.builder()
                .user_name(this.user_name)
                .ticket(ticket)
                .build();
    }
    public CreateReservationRequest(Long ticket_id, String user_name){
        this.ticket_id=ticket_id;
        this.user_name=user_name;
    }
}
