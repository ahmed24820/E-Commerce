package com.my_project.e_commerce.Service.service;



import com.my_project.e_commerce.Models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User Save(User user);
    List<User>findAll();
    User getById(long id);
    Optional<User> findByUsername(String username);
}
