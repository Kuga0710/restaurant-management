package com.example.Restaurant.management.services;

import com.example.Restaurant.management.dtos.ReservationDto;
import com.example.Restaurant.management.entities.Reservation;

public interface ReservationService {
    Reservation createReservation(ReservationDto reservationDto);
}
