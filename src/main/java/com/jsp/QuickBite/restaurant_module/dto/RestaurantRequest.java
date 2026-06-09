package com.jsp.QuickBite.restaurant_module.dto;


import lombok.Data;

@Data
public class RestaurantRequest {
    private String restaurantName;
    private Long phone;
    private String address;
    private Integer pincode;
    private String image;
    private Integer userId;
}
