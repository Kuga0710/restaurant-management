package com.example.Restaurant.management.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long serviceID;

    private String name;
    private String description;
    private double price;
    private String availabilityStatus;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB") // Change to LONGBLOB if needed
    private byte[] image; // Store image data as byte array


//    @ManyToOne
//    @JoinColumn(name = "reservationID")
//    private Reservation reservation;
//
//    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL)
//    private Set<OrderItem> orderItems = new HashSet<>();
}
