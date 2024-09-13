package com.my_project.e_commerce.Repos;

import com.my_project.e_commerce.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    Optional<User>findByUsername(String username);
}
