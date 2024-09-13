package com.my_project.e_commerce.Models;

import jakarta.persistence.*;
import lombok.*;

@Table
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    private int currentQuantity;
    private double salePrice;
    private double costPrice;
//    @Lob // this tell jpa that the property has a big size
//    @Column(columnDefinition = "MEDIUMBLOB",nullable = true)
//    private String image;
    private boolean isActivated;
    private boolean isDeleted;
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
    @JoinColumn(name = "category_id",referencedColumnName = "category_id")
    private Category category;

}

