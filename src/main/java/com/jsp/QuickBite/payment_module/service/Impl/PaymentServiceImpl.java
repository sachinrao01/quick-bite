package com.jsp.QuickBite.payment_module.service.Impl;

import com.jsp.QuickBite.payment_module.service.PaymentService;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Override
    public boolean processPayment(Integer userId) {
        return true;
    }

}
