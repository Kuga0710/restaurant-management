package com.example.Restaurant.management.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private Long Id;
    private String username;
    private String password;
    private String email;

}
