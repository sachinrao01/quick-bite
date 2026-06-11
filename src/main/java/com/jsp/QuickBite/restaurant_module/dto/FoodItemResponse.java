package com.jsp.QuickBite.restaurant_module.dto;

import com.jsp.QuickBite.restaurant_module.model.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodItemResponse {
    private String name;
    private String description;
    private Double price;
    private Integer stock;

    public Boolean getStock_available() {
        return stock_available;
    }

    public void setStock_available(Boolean stock_available) {
        this.stock_available = stock_available;
    }

    private Boolean stock_available;
    private Category category;
    private Long restaurantId;
    private String restaurantName;
}
