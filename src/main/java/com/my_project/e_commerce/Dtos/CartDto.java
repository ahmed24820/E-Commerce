package com.my_project.e_commerce.Dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@Builder
public class CartDto {
    private long id;
    private double totalPrice;
    private int totalItems;
    private Set<CartItemDto>cartItemSet;
}
