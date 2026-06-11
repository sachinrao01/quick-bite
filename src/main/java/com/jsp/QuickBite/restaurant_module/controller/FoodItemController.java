package com.jsp.QuickBite.restaurant_module.controller;


import com.jsp.QuickBite.restaurant_module.dto.FoodItemRequest;
import com.jsp.QuickBite.restaurant_module.dto.FoodItemResponse;
import com.jsp.QuickBite.restaurant_module.service.FoodItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quickbite/fooditem")
@RequiredArgsConstructor
public class FoodItemController {

    private final FoodItemService foodItemService;

    @PostMapping("/add")
    public ResponseEntity<FoodItemResponse> addFoodItem(
            @RequestBody FoodItemRequest foodItemRequest) {

        FoodItemResponse response =
                foodItemService.addFoodItem(foodItemRequest);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/update-stock/{foodItemId}")
    public ResponseEntity<FoodItemResponse> updateStock(
            @PathVariable Integer foodItemId,
            @RequestParam Integer newStock) {

        FoodItemResponse response = foodItemService.updateStock(foodItemId, newStock);

        return ResponseEntity.ok(response);
    }

}
