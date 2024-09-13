package com.my_project.e_commerce.Email.Repo;

import com.my_project.e_commerce.Email.Utils.EmailConfirmation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfirmationRepo extends JpaRepository<EmailConfirmation,Long> {

    Optional<EmailConfirmation>findByConfirmationToken(String token);
}
