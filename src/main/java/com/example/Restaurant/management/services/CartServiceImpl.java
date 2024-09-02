package com.example.Restaurant.management.services;

import com.example.Restaurant.management.dtos.CartDto;
import com.example.Restaurant.management.dtos.CartGetDto;
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
import java.util.stream.Collectors;

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
    public List<CartGetDto> getAllCartsByUserId(Long userId) {
        List<Cart> carts = cartRepository.findByUserId(userId);

        return carts.stream().map(cart -> {
            CartGetDto dto = new CartGetDto();
            dto.setUserId(cart.getUser().getId());
            dto.setCartId(cart.getCartId());
            dto.setMenuName(cart.getMenu().getName());
            dto.setMenuPrice(cart.getMenu().getPrice());
            dto.setQuantity(cart.getQuantity());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public CartGetDto increaseCartQuantity(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        // Increase the quantity by 1
        cart.setQuantity(cart.getQuantity() + 1);
        cartRepository.save(cart);

        return convertToCartGetDto(cart);
    }

    @Override
    public CartGetDto decreaseCartQuantity(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        // Decrease the quantity but ensure it does not go below 1
        if (cart.getQuantity() > 1) {
            cart.setQuantity(cart.getQuantity() - 1);
            cartRepository.save(cart);
            return convertToCartGetDto(cart);
        } else {
            return null; // If quantity is already 1, return null
        }
    }

    private CartGetDto convertToCartGetDto(Cart cart) {
        CartGetDto cartGetDto = new CartGetDto();
        cartGetDto.setUserId(cart.getUser().getId());
        cartGetDto.setMenuName(cart.getMenu().getName());
        cartGetDto.setMenuPrice(cart.getMenu().getPrice());
        cartGetDto.setQuantity(cart.getQuantity());
        return cartGetDto;
    }

}
