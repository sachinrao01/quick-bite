package com.jsp.QuickBite.cart_module.service.Impl;

import com.jsp.QuickBite.cart_module.dao.CartRepository;
import com.jsp.QuickBite.cart_module.dto.CartRequest;
import com.jsp.QuickBite.cart_module.dto.CartResponse;
import com.jsp.QuickBite.cart_module.model.Cart;
import com.jsp.QuickBite.cart_module.model.CartItem;
import com.jsp.QuickBite.cart_module.service.CartService;
import com.jsp.QuickBite.restaurant_module.dao.FoodItemRepository;
import com.jsp.QuickBite.restaurant_module.model.FoodItem;
import com.jsp.QuickBite.user_module.dao.UserRepository;
import com.jsp.QuickBite.user_module.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final FoodItemRepository foodItemRepository;
    @Override
    public CartResponse createCart(CartRequest cartRequest) {

        User user = userRepository.findById(cartRequest.getUserId().intValue())
                .orElseThrow(() -> new RuntimeException("User not found"));

        FoodItem foodItem = foodItemRepository.findById(cartRequest.getFoodItemId().intValue())
                .orElseThrow(() -> new RuntimeException("Food Item not found"));


        Cart cart = cartRepository.findByUser(user)
                .orElse(null);


        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            cart.setItems(new java.util.ArrayList<>());
            cart.setTotalPrice(0.0);
        }

        CartItem cartItem = new CartItem();
        cartItem.setFoodItemId(foodItem.getId().longValue());
        cartItem.setFoodName(foodItem.getName());
        cartItem.setDescription(foodItem.getDescription());
        cartItem.setPrice(foodItem.getPrice());
        cartItem.setCategory(foodItem.getCategory());
        cartItem.setQuantity(1);

        cartItem.setCart(cart);


        cart.getItems().add(cartItem);


        cart.setTotalPrice(cart.getTotalPrice() + foodItem.getPrice());

        Cart savedCart = cartRepository.save(cart);

        CartResponse response = new CartResponse();
        response.setFoodName(cartItem.getFoodName());
        response.setDescription(cartItem.getDescription());
        response.setPrice(cartItem.getPrice());
        response.setCategory(cartItem.getCategory());
        response.setQuantity(cartItem.getQuantity());
        response.setTotalPrice(savedCart.getTotalPrice());
        response.setItems(savedCart.getItems());

        return response;
    }

    @Override
    public CartResponse findByUserId(Long userId) {

        User user = userRepository.findById(userId.intValue())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        CartResponse response = new CartResponse();

        if (!cart.getItems().isEmpty()) {
            CartItem item = cart.getItems().get(0);

            response.setFoodName(item.getFoodName());
            response.setDescription(item.getDescription());
            response.setPrice(item.getPrice());
            response.setCategory(item.getCategory());
            response.setQuantity(item.getQuantity());
        }

        response.setTotalPrice(cart.getTotalPrice());
        response.setItems(cart.getItems());

        return response;
    }

    @Override
    public CartResponse addItemToCart(CartRequest cartRequest) {

        User user = userRepository.findById(cartRequest.getUserId().intValue())
                .orElseThrow(() -> new RuntimeException("User not found"));

        FoodItem foodItem = foodItemRepository.findById(cartRequest.getFoodItemId().intValue())
                .orElseThrow(() -> new RuntimeException("Food Item not found"));

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Cart not found"));


        CartItem existingItem = null;

        for (CartItem item : cart.getItems()) {
            if (item.getFoodItemId().equals(foodItem.getId().longValue())) {
                existingItem = item;
                break;
            }
        }

        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + 1);
        } else {

            CartItem cartItem = new CartItem();
            cartItem.setFoodItemId(foodItem.getId().longValue());
            cartItem.setFoodName(foodItem.getName());
            cartItem.setDescription(foodItem.getDescription());
            cartItem.setPrice(foodItem.getPrice());
            cartItem.setCategory(foodItem.getCategory());
            cartItem.setQuantity(1);
            cartItem.setCart(cart);
            cart.getItems().add(cartItem);
        }

        double total = 0.0;

        for (CartItem item : cart.getItems()) {
            total += item.getPrice() * item.getQuantity();
        }

        cart.setTotalPrice(total);

        Cart savedCart = cartRepository.save(cart);

        CartResponse response = new CartResponse();
        response.setItems(savedCart.getItems());
        response.setTotalPrice(savedCart.getTotalPrice());

        return response;
    }

    @Override
    public CartResponse increaseItemQuantity(CartRequest cartRequest) {

        User user = userRepository.findById(cartRequest.getUserId().intValue())
                .orElseThrow(() -> new RuntimeException("User not found"));

        FoodItem foodItem = foodItemRepository.findById(cartRequest.getFoodItemId().intValue())
                .orElseThrow(() -> new RuntimeException("Food Item not found"));

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        CartItem targetItem = null;

        for (CartItem item : cart.getItems()) {
            if (item.getFoodItemId().equals(foodItem.getId().longValue())) {
                targetItem = item;
                break;
            }
        }

        if (targetItem == null) {
            throw new RuntimeException("Item not found in cart");
        }

        targetItem.setQuantity(targetItem.getQuantity() + 1);

        double total = 0.0;

        for (CartItem item : cart.getItems()) {
            total += item.getPrice() * item.getQuantity();
        }

        cart.setTotalPrice(total);

        Cart savedCart = cartRepository.save(cart);

        CartResponse response = new CartResponse();
        response.setItems(savedCart.getItems());
        response.setTotalPrice(savedCart.getTotalPrice());

        return response;
    }
    @Override
    public CartResponse decreaseItemQuantity(CartRequest cartRequest) {

        User user = userRepository.findById(cartRequest.getUserId().intValue())
                .orElseThrow(() -> new RuntimeException("User not found"));

        FoodItem foodItem = foodItemRepository.findById(cartRequest.getFoodItemId().intValue())
                .orElseThrow(() -> new RuntimeException("Food Item not found"));

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        CartItem targetItem = null;

        for (CartItem item : cart.getItems()) {
            if (item.getFoodItemId().equals(foodItem.getId().longValue())) {
                targetItem = item;
                break;
            }
        }

        if (targetItem == null) {
            throw new RuntimeException("Item not found in cart");
        }

        int qty = targetItem.getQuantity();

        if (qty > 1) {
            targetItem.setQuantity(qty - 1);
        } else {

            cart.getItems().remove(targetItem);
        }

        double total = 0.0;

        for (CartItem item : cart.getItems()) {
            total += item.getPrice() * item.getQuantity();
        }

        cart.setTotalPrice(total);

        Cart savedCart = cartRepository.save(cart);

        CartResponse response = new CartResponse();
        response.setItems(savedCart.getItems());
        response.setTotalPrice(savedCart.getTotalPrice());

        return response;
    }




}
