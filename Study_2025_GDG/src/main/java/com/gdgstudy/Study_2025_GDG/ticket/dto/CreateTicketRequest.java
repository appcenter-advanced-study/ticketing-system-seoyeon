package com.gdgstudy.Study_2025_GDG.ticket.dto;

import com.gdgstudy.Study_2025_GDG.ticket.Ticket;
import lombok.Getter;

@Getter
public class CreateTicketRequest {
    private String name;

    public Ticket toEntity(){
        return Ticket.builder()
                .name(this.name)
                .build();
    }
}
