package com.my_project.e_commerce.Mapper;

import com.my_project.e_commerce.Dtos.CartItemDto;
import com.my_project.e_commerce.Models.CartItem;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 3.42.50.v20250729-0351, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class CartItemMapperImpl implements CartItemMapper {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public CartItemDto map(CartItem cartItem) {
        if ( cartItem == null ) {
            return null;
        }

        CartItemDto cartItemDto = new CartItemDto();

        cartItemDto.setId( cartItem.getId() );
        cartItemDto.setProduct( productMapper.map( cartItem.getProduct() ) );
        cartItemDto.setQuantity( cartItem.getQuantity() );

        return cartItemDto;
    }

    @Override
    public CartItem unmap(CartItemDto dto) {
        if ( dto == null ) {
            return null;
        }

        CartItem cartItem = new CartItem();

        cartItem.setId( dto.getId() );
        cartItem.setProduct( productMapper.unMap( dto.getProduct() ) );
        cartItem.setQuantity( dto.getQuantity() );

        return cartItem;
    }
}
