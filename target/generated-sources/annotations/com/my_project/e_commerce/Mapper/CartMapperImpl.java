package com.my_project.e_commerce.Mapper;

import com.my_project.e_commerce.Dtos.CartDto;
import com.my_project.e_commerce.Dtos.CartDto.CartDtoBuilder;
import com.my_project.e_commerce.Dtos.CartItemDto;
import com.my_project.e_commerce.Models.CartItem;
import com.my_project.e_commerce.Models.ShoppingCart;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 3.42.50.v20250729-0351, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class CartMapperImpl implements CartMapper {

    @Autowired
    private CartItemMapper cartItemMapper;

    @Override
    public CartDto map(ShoppingCart shoppingCart) {
        if ( shoppingCart == null ) {
            return null;
        }

        CartDtoBuilder cartDto = CartDto.builder();

        cartDto.cartItemSet( cartItemSetToCartItemDtoSet( shoppingCart.getCartitemSet() ) );
        cartDto.id( shoppingCart.getId() );
        cartDto.totalItems( shoppingCart.getTotalItems() );
        cartDto.totalPrice( shoppingCart.getTotalPrice() );

        return cartDto.build();
    }

    @Override
    public ShoppingCart unmap(CartDto dto) {
        if ( dto == null ) {
            return null;
        }

        ShoppingCart shoppingCart = new ShoppingCart();

        shoppingCart.setId( dto.getId() );
        shoppingCart.setTotalItems( dto.getTotalItems() );
        shoppingCart.setTotalPrice( dto.getTotalPrice() );

        return shoppingCart;
    }

    protected Set<CartItemDto> cartItemSetToCartItemDtoSet(Set<CartItem> set) {
        if ( set == null ) {
            return null;
        }

        Set<CartItemDto> set1 = new HashSet<CartItemDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( CartItem cartItem : set ) {
            set1.add( cartItemMapper.map( cartItem ) );
        }

        return set1;
    }
}
