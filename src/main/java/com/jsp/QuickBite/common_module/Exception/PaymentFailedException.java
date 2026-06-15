package com.jsp.QuickBite.common_module.Exception;

public class PaymentFailedException extends RuntimeException {
    public PaymentFailedException(String message) {
        super(message);
    }

}
