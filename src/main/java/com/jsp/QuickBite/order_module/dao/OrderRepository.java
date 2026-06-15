package com.jsp.QuickBite.order_module.dao;

import com.jsp.QuickBite.order_module.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface OrderRepository extends JpaRepository<Order,Long> {

   List <Order> findByUserId(Integer userId);
}
