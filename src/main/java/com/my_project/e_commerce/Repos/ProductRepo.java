package com.my_project.e_commerce.Repos;

import com.my_project.e_commerce.Models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
     Product findByName(String name);

     Page<Product>findAll(Pageable pageable);

     @Query("select p from Product p where p.description like ?1 or p.name like ?1")
     Page<Product>SearchProduct(String keyword , Pageable pageable);
}
