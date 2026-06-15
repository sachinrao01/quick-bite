package com.jsp.QuickBite.cart_module.model;



import com.jsp.QuickBite.restaurant_module.model.Category;

import jakarta.persistence.*;
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
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;
    private Long foodItemId;
    private Integer quantity;
    private Double price;
    private String foodName;
    private String description;
    private Category category;
    private String imageUrl;

}


























