package com.example.Restaurant.management.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Query {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long queryID;

    private String queryText;
    private String responseText;
    private String status;

    @ManyToOne
    @JoinColumn(name = "userID", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "restaurantID", nullable = false)
    private Restaurant restaurant;
}
