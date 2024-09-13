package com.my_project.e_commerce.Repos;


import com.my_project.e_commerce.Models.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface OrderDetailRepo extends JpaRepository<OrderDetail,Long> {
}
