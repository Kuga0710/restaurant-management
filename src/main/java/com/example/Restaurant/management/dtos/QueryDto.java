package com.example.Restaurant.management.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QueryDto {

    private Long queryId;
    private String name;
    private String email;
    private String subject;
    private String message;
}
