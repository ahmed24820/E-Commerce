package com.my_project.e_commerce.Service.service;



import com.my_project.e_commerce.Models.User;

import java.util.List;

public interface UserService {
    User Save(User customer);
    List<User>findAll();
    User getById(long id);
    User findByUsername(String username);
}
