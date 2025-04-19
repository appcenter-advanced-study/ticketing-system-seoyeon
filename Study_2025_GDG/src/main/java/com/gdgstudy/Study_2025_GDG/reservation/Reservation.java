package com.gdgstudy.Study_2025_GDG.reservation;

import com.gdgstudy.Study_2025_GDG.reservation.dto.CreateReservationResponse;
import com.gdgstudy.Study_2025_GDG.ticket.Ticket;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservation_id;

    private String user_name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_id", nullable = false)
    private Ticket ticket;

    @Builder
    private Reservation(String user_name, Ticket ticket){
        this.user_name=user_name; this.ticket=ticket;
    }

    public CreateReservationResponse toDto(){
        return CreateReservationResponse.builder()
                .reservation_id(this.reservation_id)
                .ticket_id(this.ticket.getTicket_id())
                .user_name(this.user_name)
                .build();
    }
}
