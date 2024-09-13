package com.my_project.e_commerce.Exceptions;

public class JWTExpiration extends RuntimeException{
    public JWTExpiration(String message){
        super(message);
    }
    public JWTExpiration(){}
}
