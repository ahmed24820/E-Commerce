package com.my_project.e_commerce.Mapper;

import com.my_project.e_commerce.Dtos.CategoryDto;
import com.my_project.e_commerce.Models.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CategoryMapper {
  CategoryDto map(Category category);
  Category unmap(CategoryDto categoryDto);

}
