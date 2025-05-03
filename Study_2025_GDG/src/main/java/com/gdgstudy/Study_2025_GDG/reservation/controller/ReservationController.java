package com.gdgstudy.Study_2025_GDG.reservation.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gdgstudy.Study_2025_GDG.reservation.dto.CreateReservationRequest;
import com.gdgstudy.Study_2025_GDG.reservation.dto.CreateReservationResponse;
import com.gdgstudy.Study_2025_GDG.reservation.repository.ReservationRepository;
import com.gdgstudy.Study_2025_GDG.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {
    //private final ReservationService reservationService;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateReservationRequest request) throws JsonProcessingException {
        //객체->메시지로
        //kafka가 메시지 기반 예약방식
        // CreateReservationResponse response = reservationService.createReservation(request);
        // return new ResponseEntity<>(response, HttpStatus.OK);
        String message = objectMapper.writeValueAsString(request);
        kafkaTemplate.send("reservation.request", message);
        return ResponseEntity.accepted().build();  // Kafka 전송 후 바로 응답
    }
}
