package com.my_project.e_commerce.Service.service;


import com.my_project.e_commerce.Models.Category;

import java.util.List;

public interface CategoryService {
    List<Category> Categories();
    Category findById(long id);
    Category save(Category category);
    Category update(long id , Category category);
     String Activate(long id);
     String Delete(long id);
     List<Category>Categories_Activated();
}
