package com.my_project.e_commerce.Email.Utils;

import org.springframework.stereotype.Component;

@Component
public class EmailUtils {

    public static String getEmailMessage(String name, String host, String token){
        return "hello " + name + "\n\n this account has been created. please click this link to verify your account"+
                "\n\n" + verified(host,token) + "\n\n" +"the support team" ;
    }

    public static String verified(String host, String token) {
        return host +"/api/v1/auth?token=" + token;
    }

}
