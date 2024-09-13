package com.my_project.e_commerce.Dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private long id;
    private String name;
    private String description;
    private double costPrice;
    private CategoryDto category;
    private int currentQuantity;
}
