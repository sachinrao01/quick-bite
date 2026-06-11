package com.jsp.QuickBite.cart_module.service;

import com.jsp.QuickBite.cart_module.dto.CartRequest;
import com.jsp.QuickBite.cart_module.dto.CartResponse;

public interface CartService {
    CartResponse createCart(CartRequest cartRequest);
    CartResponse findByUserId(Long userId);
    CartResponse addItemToCart(CartRequest cartRequest);
    CartResponse increaseItemQuantity(CartRequest cartRequest);
    CartResponse decreaseItemQuantity(CartRequest cartRequest);
}
