package com.gdgstudy.Study_2025_GDG.reservation.service;

import com.gdgstudy.Study_2025_GDG.ticket.Ticket;
import com.gdgstudy.Study_2025_GDG.ticket.dto.CreateTicketRequest;
import com.gdgstudy.Study_2025_GDG.ticket.service.TicketService;
import com.gdgstudy.Study_2025_GDG.reservation.dto.CreateReservationRequest;
import com.gdgstudy.Study_2025_GDG.ticketstock.repository.TicketStockRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
@Transactional
class ReservationServiceConcurrencyTest {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private TicketStockRepository ticketStockRepository;

    private Long ticketId;

    @BeforeEach
    void setUp() {
        CreateTicketRequest request = new CreateTicketRequest("Test Ticket");
        this.ticketId = ticketService.createTicket(request).getId();
    }

    @Test
    void 동시에_20명이_예약을_요청하면_10개까지만_예약된다() throws InterruptedException {
        int numberOfThreads = 20;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        List<Future<Boolean>> futures = new ArrayList<>();

        for (int i = 0; i < numberOfThreads; i++) {
            final int userNumber = i;
            futures.add(executorService.submit(() -> {
                try {
                    CreateReservationRequest request = new CreateReservationRequest(ticketId, "user" + userNumber);
                    reservationService.createReservation(request);
                    return true; // 성공
                } catch (Exception e) {
                    return false; // 실패
                } finally {
                    latch.countDown();
                }
            }));
        }

        latch.await(); // 모든 작업 완료 대기

        long successCount = futures.stream().filter(future -> {
            try {
                return future.get();
            } catch (Exception e) {
                return false;
            }
        }).count();

        long failCount = numberOfThreads - successCount;

        // 남은 재고 조회
        Ticket ticket = ticketService.getTicket(ticketId);
        int ticketStock = ticketStockRepository.findByTicket(ticket)
                .orElseThrow(() -> new RuntimeException("TicketStock not found"))
                .getQuantity();

        // 결과 출력
        System.out.println("===================================");
        System.out.println("성공한 예약 건수: " + successCount);
        System.out.println("실패한 예약 건수: " + failCount);
        System.out.println("남은 재고 수량: " + ticketStock);
        System.out.println("===================================");

        // 검증
        assertThat(successCount).isEqualTo(10);
        assertThat(ticketStock).isEqualTo(0);
    }
}

