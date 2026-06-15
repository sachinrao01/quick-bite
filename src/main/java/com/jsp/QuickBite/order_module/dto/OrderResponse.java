package com.jsp.QuickBite.order_module.dto;

import com.jsp.QuickBite.order_module.model.OrderItem;
import com.jsp.QuickBite.order_module.model.OrderStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.util.List;

@Data
public class OrderResponse {
private List<OrderItem> orderItems;
private Double totalAmount;
@Enumerated(EnumType.STRING)
private OrderStatus orderStatus;
// order status
}
