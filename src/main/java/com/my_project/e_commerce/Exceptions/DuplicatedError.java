package com.my_project.e_commerce.Exceptions;

public class DuplicatedError extends RuntimeException{
    public DuplicatedError(String message){
        super(message);
    }
    public DuplicatedError(){

    }
}
