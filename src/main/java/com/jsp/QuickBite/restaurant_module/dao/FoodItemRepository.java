package com.jsp.QuickBite.restaurant_module.dao;

import com.jsp.QuickBite.restaurant_module.model.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodItemRepository extends JpaRepository<FoodItem, Integer> {



}
