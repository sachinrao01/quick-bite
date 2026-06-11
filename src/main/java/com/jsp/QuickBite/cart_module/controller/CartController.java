package com.jsp.QuickBite.cart_module.controller;


import com.jsp.QuickBite.cart_module.dto.CartRequest;
import com.jsp.QuickBite.cart_module.dto.CartResponse;
import com.jsp.QuickBite.cart_module.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping ("/quickbite/cart")
public class CartController {

    private final CartService cartService;
    @PostMapping("/createCart")
    public ResponseEntity<CartResponse> createCart(
            @RequestBody CartRequest cartRequest) {

        CartResponse response = cartService.createCart(cartRequest);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<CartResponse> findCartByUserId(
            @PathVariable Long userId) {

        CartResponse response = cartService.findByUserId(userId);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/add-item")
    public ResponseEntity<CartResponse> addItemToCart(
            @RequestBody CartRequest cartRequest) {

        CartResponse response = cartService.addItemToCart(cartRequest);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/increase-quantity")
    public ResponseEntity<CartResponse> increaseItemQuantity(
            @RequestBody CartRequest cartRequest) {

        CartResponse response = cartService.increaseItemQuantity(cartRequest);

        return ResponseEntity.ok(response);
    }
    @PostMapping("/decrease-quantity")
    public ResponseEntity<CartResponse> decreaseItemQuantity(
            @RequestBody CartRequest cartRequest) {

        CartResponse response = cartService.decreaseItemQuantity(cartRequest);

        return ResponseEntity.ok(response);
    }

}
