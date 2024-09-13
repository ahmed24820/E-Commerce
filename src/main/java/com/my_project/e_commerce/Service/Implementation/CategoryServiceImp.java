package com.my_project.e_commerce.Service.Implementation;


import com.my_project.e_commerce.Models.Category;
import com.my_project.e_commerce.Repos.CategoryRepo;
import com.my_project.e_commerce.Service.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryServiceImp implements CategoryService {

     private final CategoryRepo repo;

    @Override
    public List<Category> Categories() {
        return repo.findAll();
    }

    @Override
    public Category findById(long id) {
        return repo.findById(id).get();
    }

    @Override
    public Category save(Category category) {
        Category newCategory = new Category(category.getName());
        newCategory.setName(category.getName());
        newCategory.setActivated(true);
        newCategory.setDeleted(false);
        return repo.save(newCategory);
    }

    @Override
    public Category update(long id , Category category) {
       Category category1 = repo.findById(id).get();
       category1.setName(category.getName());
        return repo.save(category1);
    }

    @Override
    public String Activate(long id) {
        Category category = repo.findById(id).get();
        category.setActivated(true);
        category.setDeleted(false);
          repo.save(category);
      return "Activated Successfully";
    }

    @Override
    public String Delete(long id) {
        Category category = repo.findById(id).get();
        category.setActivated(false);
        category.setDeleted(true);
        repo.save(category);
        return "Deleted Successfully";
    }

    @Override
    public List<Category> Categories_Activated() {
        return repo.findAllWithActivate();
    }
}
