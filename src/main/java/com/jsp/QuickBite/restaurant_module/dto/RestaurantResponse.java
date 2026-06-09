package com.jsp.QuickBite.restaurant_module.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RestaurantResponse {
    private String restaurantName;
    private Long phone;
    private String address;
   private Integer pincode;
    private String image;

}
