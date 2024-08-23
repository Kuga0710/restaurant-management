//package com.example.Restaurant.management.entities;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//
//@Entity
//@Getter
//@Setter
//public class OrderItem {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long orderItemID;
//
//    private int quantity;
//    private double price;
//
//    @ManyToOne
//    @JoinColumn(name = "orderID")
//    private Order order;
//
//    @ManyToOne
//    @JoinColumn(name = "serviceID")
//    private Service service;
//}
