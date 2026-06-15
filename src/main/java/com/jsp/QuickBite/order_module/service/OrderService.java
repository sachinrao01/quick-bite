package com.jsp.QuickBite.order_module.service;

import com.jsp.QuickBite.order_module.dto.OrderResponse;
import com.jsp.QuickBite.user_module.model.User;

public interface OrderService {
    OrderResponse order(User user);
}
