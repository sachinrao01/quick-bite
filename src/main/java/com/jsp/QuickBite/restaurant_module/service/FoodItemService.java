package com.jsp.QuickBite.restaurant_module.service;

import com.jsp.QuickBite.restaurant_module.dto.FoodItemRequest;
import com.jsp.QuickBite.restaurant_module.dto.FoodItemResponse;

//addfooditem
// update Stock
public interface FoodItemService  {
    FoodItemResponse addFoodItem(FoodItemRequest foodItemRequest);
    FoodItemResponse updateStock(Integer foodItemId, Integer newStock);

}
