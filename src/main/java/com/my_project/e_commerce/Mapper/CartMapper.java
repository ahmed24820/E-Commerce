package com.my_project.e_commerce.Mapper;

import com.my_project.e_commerce.Dtos.CartDto;
import com.my_project.e_commerce.Models.ShoppingCart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = CartItemMapper.class)
public interface CartMapper {
@Mapping(source = "cartitemSet",target ="cartItemSet")
    CartDto map(ShoppingCart shoppingCart);
    ShoppingCart unmap(CartDto dto);
}
