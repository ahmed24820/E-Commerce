package com.my_project.e_commerce.Mapper;

import com.my_project.e_commerce.Dtos.ProductDto;
import com.my_project.e_commerce.Models.Product;
import org.mapstruct.Mapper;

@Mapper(uses = CategoryMapper.class)
public interface ProductMapper {
    ProductDto map (Product product);
    Product unMap(ProductDto dto);
}
