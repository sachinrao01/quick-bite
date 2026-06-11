package com.jsp.QuickBite.restaurant_module.model;

import com.jsp.QuickBite.user_module.model.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "restaurant_seq", allocationSize = 1,initialValue = 10000)
    private Long restaurantId;
    private String restaurantName;
    private Long phone;
    private String address;
    private Integer pincode;
    private String image;
    @OneToOne
    @JoinColumn(name = "manager_id")
    private User manager;

    @OneToMany(mappedBy = "restaurant" , cascade = CascadeType.ALL)
    private List<FoodItem> foodItems;



}
