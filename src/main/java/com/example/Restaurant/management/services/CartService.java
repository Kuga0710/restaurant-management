package com.example.Restaurant.management.services;

import com.example.Restaurant.management.dtos.CartDto;
import com.example.Restaurant.management.entities.Cart;

public interface CartService {
    Cart createCart(CartDto cartDto);
    boolean deleteAllCarts();
    boolean deleteCartById(Long id);
}
