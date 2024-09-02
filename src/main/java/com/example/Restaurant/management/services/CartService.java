package com.example.Restaurant.management.services;

import com.example.Restaurant.management.dtos.CartDto;
import com.example.Restaurant.management.dtos.CartGetDto;
import com.example.Restaurant.management.entities.Cart;

import java.util.List;

public interface CartService {
    Cart createCart(CartDto cartDto);
    boolean deleteAllCarts();
    boolean deleteCartById(Long id);
    List<CartGetDto> getAllCartsByUserId(Long userId);
    CartGetDto increaseCartQuantity(Long cartId);
    CartGetDto decreaseCartQuantity(Long cartId);
}
