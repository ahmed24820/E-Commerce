package com.my_project.e_commerce.Dtos;

import lombok.Data;

@Data
public class CartItemDto {
   private long id;
   private int quantity;
   private ProductDto product;
}
