package com.my_project.e_commerce.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Setter
@Getter
@Table
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne()
    @JoinColumn(name = "user-id")
    private User user;
    private double totalPrice;
    private int totalItems;

    @OneToMany(mappedBy = "shoppingCart",fetch = FetchType.EAGER)
    private Set<CartItem>  cartitemSet;
    }


