package com.example.Restaurant.management.services;

import com.example.Restaurant.management.dtos.CartDto;
import com.example.Restaurant.management.entities.Cart;
import com.example.Restaurant.management.entities.Menu;
import com.example.Restaurant.management.entities.User;
import com.example.Restaurant.management.repositories.CartRepository;
import com.example.Restaurant.management.repositories.MenuRepository;
import com.example.Restaurant.management.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Override
    public Cart createCart(CartDto cartDto) {
        Cart cart = new Cart();

        User user = userRepository.findById(cartDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Menu menu = menuRepository.findById(cartDto.getMenuId())
                .orElseThrow(() -> new RuntimeException("Menu item not found"));

        cart.setUser(user);
        cart.setMenu(menu);
        cart.setQuantity(1); // Quantity is explicitly set to 1

        return cartRepository.save(cart);
    }

    @Override
    public boolean deleteAllCarts() {
        try {
            cartRepository.deleteAll();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteCartById(Long id) {
        if (cartRepository.existsById(id)) {
            cartRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Cart> getAllCartsByUserId(Long userId) {
        return cartRepository.findByUserId(userId);

    }
}
