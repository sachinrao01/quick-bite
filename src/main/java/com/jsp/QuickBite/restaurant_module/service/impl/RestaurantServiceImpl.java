package com.jsp.QuickBite.restaurant_module.service.impl;




import com.jsp.QuickBite.restaurant_module.dao.RestaurantRepository;
import com.jsp.QuickBite.restaurant_module.dto.RestaurantRequest;
import com.jsp.QuickBite.restaurant_module.dto.RestaurantResponse;
import com.jsp.QuickBite.restaurant_module.model.Restaurant;
import com.jsp.QuickBite.restaurant_module.service.RestaurantService;
import com.jsp.QuickBite.user_module.dao.UserRepository;
import com.jsp.QuickBite.user_module.exception.UserException;
import com.jsp.QuickBite.user_module.model.User;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


    @Service
    @RequiredArgsConstructor
    public class RestaurantServiceImpl implements RestaurantService {
        private final UserRepository userRepository;
        private final RestaurantRepository restaurantRepository;

        @Override
        public RestaurantResponse createRestaurant(RestaurantRequest restaurantRequest) {

            Integer managerId = restaurantRequest.getUserId();

            User user = userRepository.findById(managerId)
                    .orElseThrow(() -> new UserException("User not found"));

            Restaurant restaurant = new Restaurant();

            restaurant.setRestaurantName(restaurantRequest.getRestaurantName());
            restaurant.setPhone(restaurantRequest.getPhone());
            restaurant.setAddress(restaurantRequest.getAddress());
            restaurant.setPincode(restaurantRequest.getPincode());
            restaurant.setImage(restaurantRequest.getImage());
            restaurant.setManager(user);

            Restaurant savedRestaurant = restaurantRepository.save(restaurant);

            RestaurantResponse response = new RestaurantResponse();

            response.setRestaurantName(savedRestaurant.getRestaurantName());
            response.setPhone(savedRestaurant.getPhone());
            response.setAddress(savedRestaurant.getAddress());
            response.setPincode(savedRestaurant.getPincode());
            response.setImage(savedRestaurant.getImage());

            return response;
        }

        @Override
        public List<RestaurantResponse> getAllRestaurants() {
            List<Restaurant> restaurants = restaurantRepository.findAll();
            List<RestaurantResponse> responses = new ArrayList();
            for (Restaurant restaurant : restaurants) {
                RestaurantResponse response = new RestaurantResponse();
                response.setRestaurantName(restaurant.getRestaurantName());
                response.setPhone(restaurant.getPhone());
                response.setAddress(restaurant.getAddress());
                response.setPincode(restaurant.getPincode());
                response.setImage(restaurant.getImage());
                responses.add(response);
            }
            return responses;
        }

        @Override
        public List<RestaurantResponse> getRestaurantsByLocation(String location) {
            List<Restaurant> restaurants = restaurantRepository.findByAddress(location);
            List<RestaurantResponse> responses = new ArrayList();
            for (Restaurant restaurant : restaurants) {
                RestaurantResponse response = new RestaurantResponse();
                response.setRestaurantName(restaurant.getRestaurantName());
                response.setPhone(restaurant.getPhone());
                response.setAddress(restaurant.getAddress());
                response.setPincode(restaurant.getPincode());
                response.setImage(restaurant.getImage());
                responses.add(response);
            }
            return responses;
        }

        @Override
        public List<RestaurantResponse> getRestaurantsByName(String name) {
            List<Restaurant> restaurants = restaurantRepository.findByRestaurantName(name);
            List<RestaurantResponse> responses = new ArrayList();
            for (Restaurant restaurant : restaurants) {
                RestaurantResponse response = new RestaurantResponse();
                response.setRestaurantName(restaurant.getRestaurantName());
                response.setPhone(restaurant.getPhone());
                response.setAddress(restaurant.getAddress());
                response.setPincode(restaurant.getPincode());
                response.setImage(restaurant.getImage());
                responses.add(response);
            }
            return responses;
        }
        @Override
        public RestaurantResponse updateManager(Long restaurantId, Integer managerId) {

            Restaurant restaurant = restaurantRepository.findById(restaurantId)
                    .orElseThrow(() -> new RuntimeException("Restaurant not found"));

            User manager = userRepository.findById(managerId)
                    .orElseThrow(() -> new UserException("User not found"));

            restaurant.setManager(manager);

            Restaurant updatedRestaurant = restaurantRepository.save(restaurant);

            RestaurantResponse response = new RestaurantResponse();

            response.setRestaurantName(updatedRestaurant.getRestaurantName());
            response.setPhone(updatedRestaurant.getPhone());
            response.setAddress(updatedRestaurant.getAddress());
            response.setPincode(updatedRestaurant.getPincode());
            response.setImage(updatedRestaurant.getImage());

            return response;
        }
    }





