package com.my_project.e_commerce.Email.Service;

public interface EmailService {
 void SendConfirmationEmail(String name , String to , String token);
}
