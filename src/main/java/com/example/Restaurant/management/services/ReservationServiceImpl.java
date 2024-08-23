package com.example.Restaurant.management.services;

import com.example.Restaurant.management.dtos.ReservationDto;
import com.example.Restaurant.management.entities.Reservation;
import com.example.Restaurant.management.repositories.ReservationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class ReservationServiceImpl implements ReservationService{

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public Reservation createReservation(ReservationDto reservationDto) {

        Reservation reservation=new Reservation();
        BeanUtils.copyProperties(reservationDto,reservation);
        reservation.setCreatedAt(Instant.now());
        return reservationRepository.save(reservation);
    }
}
