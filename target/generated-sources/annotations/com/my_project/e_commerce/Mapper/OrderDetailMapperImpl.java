package com.my_project.e_commerce.Mapper;

import com.my_project.e_commerce.Dtos.CategoryDto;
import com.my_project.e_commerce.Dtos.OrderDetailDto;
import com.my_project.e_commerce.Dtos.ProductDto;
import com.my_project.e_commerce.Models.Category;
import com.my_project.e_commerce.Models.OrderDetail;
import com.my_project.e_commerce.Models.Product;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 3.42.50.v20250729-0351, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class OrderDetailMapperImpl implements OrderDetailMapper {

    @Override
    public OrderDetailDto map(OrderDetail orderDetail) {
        if ( orderDetail == null ) {
            return null;
        }

        OrderDetailDto orderDetailDto = new OrderDetailDto();

        orderDetailDto.setProduct( productToProductDto( orderDetail.getProduct() ) );
        orderDetailDto.setProductQuantity( orderDetail.getProductQuantity() );

        return orderDetailDto;
    }

    @Override
    public OrderDetail unmap(OrderDetailDto orderDetailDto) {
        if ( orderDetailDto == null ) {
            return null;
        }

        OrderDetail orderDetail = new OrderDetail();

        orderDetail.setProduct( productDtoToProduct( orderDetailDto.getProduct() ) );
        orderDetail.setProductQuantity( orderDetailDto.getProductQuantity() );

        return orderDetail;
    }

    protected CategoryDto categoryToCategoryDto(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDto categoryDto = new CategoryDto();

        categoryDto.setId( category.getId() );
        categoryDto.setName( category.getName() );

        return categoryDto;
    }

    protected ProductDto productToProductDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDto productDto = new ProductDto();

        productDto.setCategory( categoryToCategoryDto( product.getCategory() ) );
        productDto.setCostPrice( product.getCostPrice() );
        productDto.setCurrentQuantity( product.getCurrentQuantity() );
        productDto.setDescription( product.getDescription() );
        productDto.setId( product.getId() );
        productDto.setName( product.getName() );

        return productDto;
    }

    protected Category categoryDtoToCategory(CategoryDto categoryDto) {
        if ( categoryDto == null ) {
            return null;
        }

        Category category = new Category();

        category.setId( categoryDto.getId() );
        category.setName( categoryDto.getName() );

        return category;
    }

    protected Product productDtoToProduct(ProductDto productDto) {
        if ( productDto == null ) {
            return null;
        }

        Product product = new Product();

        product.setCategory( categoryDtoToCategory( productDto.getCategory() ) );
        product.setCostPrice( productDto.getCostPrice() );
        product.setCurrentQuantity( productDto.getCurrentQuantity() );
        product.setDescription( productDto.getDescription() );
        product.setId( productDto.getId() );
        product.setName( productDto.getName() );

        return product;
    }
}
