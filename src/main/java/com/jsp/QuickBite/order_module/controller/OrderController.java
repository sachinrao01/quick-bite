package com.jsp.QuickBite.order_module.controller;


import com.jsp.QuickBite.order_module.dto.OrderResponse;

import com.jsp.QuickBite.order_module.service.OrderService;
import com.jsp.QuickBite.user_module.dao.UserRepository;
import com.jsp.QuickBite.user_module.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quickbite/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final UserRepository userRepository;

    @PostMapping ("/{userId}")
    public ResponseEntity<OrderResponse> placeOrder(
            @PathVariable Integer userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        OrderResponse response = orderService.order(user);

        return ResponseEntity.ok(response);
    }
}
