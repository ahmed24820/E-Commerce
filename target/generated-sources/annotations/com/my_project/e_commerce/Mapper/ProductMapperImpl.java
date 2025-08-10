package com.my_project.e_commerce.Mapper;

import com.my_project.e_commerce.Dtos.ProductDto;
import com.my_project.e_commerce.Models.Product;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 3.42.50.v20250729-0351, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public ProductDto map(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDto productDto = new ProductDto();

        productDto.setCategory( categoryMapper.map( product.getCategory() ) );
        productDto.setCostPrice( product.getCostPrice() );
        productDto.setCurrentQuantity( product.getCurrentQuantity() );
        productDto.setDescription( product.getDescription() );
        productDto.setId( product.getId() );
        productDto.setName( product.getName() );

        return productDto;
    }

    @Override
    public Product unMap(ProductDto dto) {
        if ( dto == null ) {
            return null;
        }

        Product product = new Product();

        product.setCategory( categoryMapper.unmap( dto.getCategory() ) );
        product.setCostPrice( dto.getCostPrice() );
        product.setCurrentQuantity( dto.getCurrentQuantity() );
        product.setDescription( dto.getDescription() );
        product.setId( dto.getId() );
        product.setName( dto.getName() );

        return product;
    }
}
