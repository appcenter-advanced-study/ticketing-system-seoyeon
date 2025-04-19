package com.gdgstudy.Study_2025_GDG.ticket.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateTicketResponse {
    private Long id;
    private String name;

    @Builder CreateTicketResponse(Long id, String name){
        this.id=id;
        this.name=name;
    }
}
