package com.example.Restaurant.management.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private String orderStatus;
    private double totalAmount;

    @ManyToOne
    @JoinColumn(name = "userID", nullable = false)
    private User user;

//    @ManyToOne
//    @JoinColumn(name = "restaurantID", nullable = false)
//    private Restaurant restaurant;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderItem> orderItems = new HashSet<>();

//    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
//    private Payment payment;
}
