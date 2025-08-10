package com.my_project.e_commerce.Service.Implementation;

import com.my_project.e_commerce.Models.User;
import com.my_project.e_commerce.Repos.UserRepo;
import com.my_project.e_commerce.Service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
    private final UserRepo repo;
    @Override
    public User Save(User user) {
        return repo.save(user);
    }

    @Override
    public List<User> findAll() {
        return repo.findAll();
    }

    @Override
    public User getById(long id) {
        return repo.findById(id).get();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return repo.findByUsername(username);
    }
}
