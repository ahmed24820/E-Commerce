package com.my_project.e_commerce.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table
@Entity
@Setter
@Getter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Role_id")
    private int id;
    @Column(unique = true , nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleEnum name;

    private String description;
}
