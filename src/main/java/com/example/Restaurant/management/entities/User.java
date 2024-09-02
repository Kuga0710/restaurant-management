package com.example.Restaurant.management.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Getter
@Setter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private String password;



////
////    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
////    private Set<Reservation> reservations = new HashSet<>();
////
////    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
////    private Set<Query> queries = new HashSet<>();
////
////    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
////    private Set<EmailNotification> emailNotifications = new HashSet<>();
////
////    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
////    private Set<Feedback> feedbacks = new HashSet<>();
////
////    @OneToMany(mappedBy = "generatedBy", cascade = CascadeType.ALL)
////    private Set<Report> reports = new HashSet<>();
////
////    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
////    private Set<UserGalleryView> userGalleryViews = new HashSet<>();
//
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private Set<Offer> offers = new HashSet<>();
//
//    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
//    private Payment payment;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cart> cartItems;

}
