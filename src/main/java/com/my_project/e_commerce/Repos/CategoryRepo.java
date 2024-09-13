package com.my_project.e_commerce.Repos;

import com.my_project.e_commerce.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {
  @Query("Select c from Category c where c.isActivated = true")
  List<Category> findAllWithActivate();
}
