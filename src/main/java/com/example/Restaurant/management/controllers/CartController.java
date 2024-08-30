package com.example.Restaurant.management.controllers;

import com.example.Restaurant.management.dtos.CartDto;
import com.example.Restaurant.management.entities.Cart;
import com.example.Restaurant.management.enums.RestApiResponseStatusCodes;
import com.example.Restaurant.management.services.CartService;
import com.example.Restaurant.management.utilities.ResponseWrapper;
import com.example.Restaurant.management.utilities.ValidationMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/cart")
@CrossOrigin(origins = "http://localhost:3000")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping
    public ResponseEntity<ResponseWrapper<Cart>> createCart(@RequestBody CartDto cartDto) {
        Cart createdCart = cartService.createCart(cartDto);

        if (createdCart != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseWrapper<>(
                    RestApiResponseStatusCodes.CREATED.getCode(),
                    ValidationMessages.SAVED_SUCCESSFULL,
                    null
            ));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper<>(
                    RestApiResponseStatusCodes.BAD_REQUEST.getCode(),
                    ValidationMessages.SAVE_FAILED,
                    null
            ));
        }
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<ResponseWrapper<Void>> deleteAllCarts() {
        boolean isDeleted = cartService.deleteAllCarts();

        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseWrapper<>(
                    RestApiResponseStatusCodes.CREATED.getCode(),
                    ValidationMessages.DELETE_SUCCESS,
                    null
            ));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper<>(
                    RestApiResponseStatusCodes.BAD_REQUEST.getCode(),
                    ValidationMessages.DELETE_FAILED,
                    null
            ));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseWrapper<Void>> deleteCartById(@PathVariable Long id) {
        boolean isDeleted = cartService.deleteCartById(id);

        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseWrapper<>(
                    RestApiResponseStatusCodes.CREATED.getCode(),
                    ValidationMessages.DELETE_SUCCESS,
                    null
            ));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper<>(
                    RestApiResponseStatusCodes.BAD_REQUEST.getCode(),
                    ValidationMessages.DELETE_FAILED,
                    null
            ));
        }
    }


}
