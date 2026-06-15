package com.jsp.QuickBite.order_module.service.Impl;

import com.jsp.QuickBite.cart_module.dao.CartRepository;
import com.jsp.QuickBite.cart_module.model.Cart;
import com.jsp.QuickBite.cart_module.model.CartItem;
import com.jsp.QuickBite.common_module.Exception.*;
import com.jsp.QuickBite.order_module.dao.OrderRepository;
import com.jsp.QuickBite.order_module.dto.OrderResponse;
import com.jsp.QuickBite.order_module.model.Order;
import com.jsp.QuickBite.order_module.model.OrderItem;
import com.jsp.QuickBite.order_module.model.OrderStatus;
import com.jsp.QuickBite.order_module.service.OrderService;
import com.jsp.QuickBite.payment_module.service.PaymentService;
import com.jsp.QuickBite.restaurant_module.dao.FoodItemRepository;
import com.jsp.QuickBite.restaurant_module.model.FoodItem;
import com.jsp.QuickBite.user_module.model.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl  implements OrderService {
    private final CartRepository cartRepository;
    private final FoodItemRepository foodItemRepository;
    private final OrderRepository orderRepository;
    private final PaymentService paymentService;
    @Override
    @Transactional
    public OrderResponse order(User user) {

        // 1. Cart find karo
        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new CartNotFoundException("Cart not found"));

        // 2. Cart empty check
        if (cart.getItems().isEmpty()) {
            throw new CartEmptyException("Cart is empty");
        }

        // 3. Stock check
        for (CartItem cartItem : cart.getItems()) {

            FoodItem foodItem = foodItemRepository
                    .findById(cartItem.getFoodItemId().intValue())
                    .orElseThrow(() -> new FoodItemNotFoundException("Food Item not found"));

            if (foodItem.getStock() < cartItem.getQuantity()) {
                throw new StockNotAvailableException(
                        foodItem.getName() + " has only "
                                + foodItem.getStock()
                                + " items available");
            }
        }

        // 4. Payment check
        boolean paymentSuccess = paymentService.processPayment(user.getId());

        if (!paymentSuccess) {
            throw new PaymentFailedException("Payment Failed");
        }

        // 5. Order create
        Order order = new Order();
        order.setUserId(user.getId());
        order.setTotalAmount(cart.getTotalPrice());
        order.setOrderStatus(OrderStatus.CONFIRM);

        List<OrderItem> orderItems = new ArrayList<>();

        // 6. CartItem -> OrderItem
        for (CartItem cartItem : cart.getItems()) {

            OrderItem orderItem = new OrderItem();
            orderItem.setFoodItemId(cartItem.getFoodItemId());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getPrice());
            orderItem.setOrder(order);

            orderItems.add(orderItem);
        }

        order.setOrderItems(orderItems);

        // 7. Save Order
        Order savedOrder = orderRepository.save(order);

        // 8. Stock update
        for (CartItem cartItem : cart.getItems()) {

            FoodItem foodItem = foodItemRepository
                    .findById(cartItem.getFoodItemId().intValue())
                    .orElseThrow(() -> new FoodItemNotFoundException("Food Item not found"));

            foodItem.setStock(
                    foodItem.getStock() - cartItem.getQuantity()
            );

            if (foodItem.getStock() <= 0) {
                foodItem.setStock_available(false);
            }

            foodItemRepository.save(foodItem);
        }

        // 9. Cart clear
        cart.getItems().clear();
        cart.setTotalPrice(0.0);
        cartRepository.save(cart);

        // 10. Response
        OrderResponse response = new OrderResponse();
        response.setOrderItems(savedOrder.getOrderItems());
        response.setTotalAmount(savedOrder.getTotalAmount());
        response.setOrderStatus(savedOrder.getOrderStatus());

        return response;
    }

}
