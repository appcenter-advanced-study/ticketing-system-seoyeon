package com.gdgstudy.Study_2025_GDG.ticket.controller;

import com.gdgstudy.Study_2025_GDG.ticket.dto.CreateTicketRequest;
import com.gdgstudy.Study_2025_GDG.ticket.dto.CreateTicketResponse;
import com.gdgstudy.Study_2025_GDG.ticket.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;

    @PostMapping
    public ResponseEntity<CreateTicketResponse> create(@RequestBody CreateTicketRequest request){
        CreateTicketResponse response = ticketService.createTicket(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
