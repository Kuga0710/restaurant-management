//package com.example.Restaurant.management.entities;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@Entity
//@Getter
//@Setter
//@Table(name = "orders")
//public class Order extends DateAudit {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long orderId;
//
//    private Double totalAmount;
//    private String paymentType;
//    private String deliveryType;
//    private String orderStatus;
//    //private String createdAt;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;
//
//    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
//    private Set<OrderItem> orderItems;
//
////    @ManyToOne
////    @JoinColumn(name = "restaurantID", nullable = false)
////    private Restaurant restaurant;
//
//
////    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
////    private Payment payment;
//}
