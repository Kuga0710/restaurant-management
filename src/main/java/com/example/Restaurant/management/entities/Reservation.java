package com.example.Restaurant.management.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationID;

    private int numberOfPeople;
    private String specialRequests;
    private String status;

    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "restaurantID")
    private Restaurant restaurant;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
    private Set<Service> services = new HashSet<>();
}
