package com.my_project.e_commerce.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    @NotNull
    private long id;
    private String name;
    private boolean isActivated;
    private boolean isDeleted;

     public Category(String name) {  // we make this constructor to set values to isDeleted and activated
         this.name = name;
         this.isActivated = true;
         this.isDeleted = false;
     }
}
