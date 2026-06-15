package com.jsp.QuickBite.cart_module.model;


import com.jsp.QuickBite.user_module.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long cartId;
        @OneToOne(cascade=CascadeType.ALL)
        @JoinColumn(name = "user_id")
        private User user;
        @OneToMany(mappedBy = "cart",cascade=CascadeType.ALL,orphanRemoval = true)
        private List<CartItem> items = new ArrayList<>();
        private Double totalPrice;
    }

