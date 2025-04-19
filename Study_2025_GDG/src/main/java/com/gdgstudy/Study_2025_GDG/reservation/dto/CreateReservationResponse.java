package com.gdgstudy.Study_2025_GDG.reservation.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateReservationResponse {
    private Long reservation_id;
    private Long ticket_id;
    private String user_name;
}
