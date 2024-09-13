package com.my_project.e_commerce.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.EAGER )
    @JoinColumn(name = "shoppingCart_id")
    private ShoppingCart shoppingCart;
    @OneToOne(fetch =FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;
    private int quantity;
    private double unitPrice;


}
