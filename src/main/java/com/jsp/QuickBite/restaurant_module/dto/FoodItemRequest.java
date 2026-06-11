package com.jsp.QuickBite.restaurant_module.dto;

import com.jsp.QuickBite.restaurant_module.model.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodItemRequest {
    private String name;
    private String description;
    private Double price;
    private Integer stock;
    private Category category;
    private Long restaurantId;
}
