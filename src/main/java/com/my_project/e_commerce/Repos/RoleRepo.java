package com.my_project.e_commerce.Repos;

import com.my_project.e_commerce.Models.Role;
import com.my_project.e_commerce.Models.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<Role,Integer> {

   Optional <Role> findByName(RoleEnum roleEnum);
}
