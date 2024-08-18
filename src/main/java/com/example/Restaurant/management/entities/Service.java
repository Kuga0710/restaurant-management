package com.example.Restaurant.management.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long serviceID;

    private String name;
    private String description;
    private double price;
    private String availabilityStatus;

    @ManyToOne
    @JoinColumn(name = "reservationID")
    private Reservation reservation;

    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL)
    private Set<OrderItem> orderItems = new HashSet<>();
}
