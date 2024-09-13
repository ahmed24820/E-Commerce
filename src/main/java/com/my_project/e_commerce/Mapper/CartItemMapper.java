package com.my_project.e_commerce.Mapper;

import com.my_project.e_commerce.Dtos.CartItemDto;
import com.my_project.e_commerce.Models.CartItem;
import org.mapstruct.Mapper;

@Mapper(uses = ProductMapper.class)
public interface CartItemMapper {
  CartItemDto map(CartItem cartItem);
  CartItem unmap(CartItemDto dto);
}
