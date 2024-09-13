package com.my_project.e_commerce.Repos;

import com.my_project.e_commerce.Models.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepo extends JpaRepository<ShoppingCart,Long> {
}
