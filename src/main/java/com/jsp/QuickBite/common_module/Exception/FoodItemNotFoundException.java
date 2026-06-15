package com.jsp.QuickBite.common_module.Exception;

public class FoodItemNotFoundException extends RuntimeException {
    public FoodItemNotFoundException(String message) {
        super(message);
    }
}
