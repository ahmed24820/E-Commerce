package com.my_project.e_commerce.Dtos;

import lombok.Data;

@Data
public class OrderDetailDto {
 private ProductDto product;
 private int ProductQuantity;
}
