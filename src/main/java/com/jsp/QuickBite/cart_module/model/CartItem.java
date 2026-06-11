package com.jsp.QuickBite.cart_module.model;



import com.jsp.QuickBite.restaurant_module.model.Category;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long cartItemId;
    private Long foodItemId;
    private Integer quantity;
    private Double price;
    private String foodName;
    private String description;
    private Category category;
    private String imageUrl;

}


























