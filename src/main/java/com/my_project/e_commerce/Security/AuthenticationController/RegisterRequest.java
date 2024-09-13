package com.my_project.e_commerce.Security.AuthenticationController;


import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class RegisterRequest {

    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String phoneNumber;
    private String address;
    private String role;


    public RegisterRequest() {

    }
}
