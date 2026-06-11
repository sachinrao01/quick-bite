package com.jsp.QuickBite.cart_module.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartRequest {
    private Long userId;
    private Long foodItemId;
}
