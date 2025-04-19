package com.gdgstudy.Study_2025_GDG.reservation.repository;

import com.gdgstudy.Study_2025_GDG.reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}