package com.my_project.e_commerce.Dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class userDto {
    // set restrictions for every property in the class
    private long id;
    @Size(min = 3 , max = 10 ,message = "first name should have 3 - 10 characters")
    private String firstname;
    @Size(min = 3 , max = 10 ,message = "last name should have 3 - 10 characters")
    private String lastname;
    @Email(message = "please follow the email style")
    private String username;



}
