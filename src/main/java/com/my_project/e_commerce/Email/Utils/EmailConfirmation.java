package com.my_project.e_commerce.Email.Utils;

import com.my_project.e_commerce.Models.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class EmailConfirmation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long TokenId;

    private String confirmationToken;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    private LocalDate date;

    public EmailConfirmation(User user){
        this.user = user;
        this.confirmationToken = UUID.randomUUID().toString();
        this.date = LocalDate.now();
    }


}
