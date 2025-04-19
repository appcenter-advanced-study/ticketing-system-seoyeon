package com.gdgstudy.Study_2025_GDG.ticket.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateTicketResponse {
    private Long id;
    private String name;
    private Integer ticket_stock;

    @Builder CreateTicketResponse(Long id, String name, Integer ticket_stock){
        this.id=id;
        this.name=name;
        this.ticket_stock=ticket_stock;
    }
}
