package com.jsp.QuickBite.restaurant_module.service;

import com.jsp.QuickBite.restaurant_module.dto.RestaurantRequest;
import com.jsp.QuickBite.restaurant_module.dto.RestaurantResponse;

import java.util.List;


//find by id
// find by loc
// find by name
//find all
//update manager
public interface RestaurantService {

    RestaurantResponse createRestaurant(RestaurantRequest restaurantRequest);
    List<RestaurantResponse> getAllRestaurants();
    List<RestaurantResponse> getRestaurantsByLocation(String location);
    List<RestaurantResponse> getRestaurantsByName(String name);
    RestaurantResponse updateManager(Long restaurantId,Integer managerId);
}
