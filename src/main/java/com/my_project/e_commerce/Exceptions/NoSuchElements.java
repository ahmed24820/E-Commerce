package com.my_project.e_commerce.Exceptions;

public class NoSuchElements extends RuntimeException{
    public NoSuchElements(String message){
        super(message);
    }
}
