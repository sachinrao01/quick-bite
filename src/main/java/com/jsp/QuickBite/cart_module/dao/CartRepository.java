package com.jsp.QuickBite.cart_module.dao;

import com.jsp.QuickBite.cart_module.model.Cart;
import com.jsp.QuickBite.user_module.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser(User user);
}















