package com.jsp.QuickBite.restaurant_module.controller;


import com.jsp.QuickBite.restaurant_module.dto.RestaurantRequest;
import com.jsp.QuickBite.restaurant_module.dto.RestaurantResponse;
import com.jsp.QuickBite.restaurant_module.service.RestaurantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quickBite/restaurant")
public class RestaurantController {
    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }
    @PostMapping("/create")
    public ResponseEntity<RestaurantResponse> createRestaurant(@RequestBody RestaurantRequest restaurantRequest) {
        return ResponseEntity.ok(restaurantService.createRestaurant(restaurantRequest));
    }

    @GetMapping("/all")
    public ResponseEntity<List<RestaurantResponse>> getAllRestaurants() {
        List<RestaurantResponse> r = restaurantService.getAllRestaurants();
        return ResponseEntity.status(HttpStatus.OK).body(r);
    }

    @GetMapping("/location")
    public ResponseEntity<List<RestaurantResponse>> getRestaurantsByLocation(@RequestParam String location){
        List<RestaurantResponse> r = restaurantService.getRestaurantsByLocation(location);
        return ResponseEntity.status(HttpStatus.OK).body(r);
    }

    @GetMapping("/name")
    public ResponseEntity<List<RestaurantResponse>> getRestaurantsByName(@RequestParam String name){
        List<RestaurantResponse> r = restaurantService.getRestaurantsByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(r);
    }

    @PutMapping("/updateManager")
    public ResponseEntity<RestaurantResponse> updateManager(@RequestParam Long restaurantId, @RequestParam Integer managerId) {
        RestaurantResponse r = restaurantService.updateManager(restaurantId, managerId);
        return ResponseEntity.status(HttpStatus.OK).body(r);
    }
}
