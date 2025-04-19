package com.gdgstudy.Study_2025_GDG.reservation.controller;

import com.gdgstudy.Study_2025_GDG.reservation.dto.CreateReservationRequest;
import com.gdgstudy.Study_2025_GDG.reservation.dto.CreateReservationResponse;
import com.gdgstudy.Study_2025_GDG.reservation.repository.ReservationRepository;
import com.gdgstudy.Study_2025_GDG.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<CreateReservationResponse> create(@RequestBody CreateReservationRequest request){
        CreateReservationResponse response = reservationService.createReservation(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
