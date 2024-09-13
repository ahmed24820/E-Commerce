package com.my_project.e_commerce.Email.Service;

import com.my_project.e_commerce.Email.Repo.ConfirmationRepo;
import com.my_project.e_commerce.Email.Utils.EmailUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class EmailServiceImp implements EmailService {
    private final ConfirmationRepo repo;
    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.verify.host}")
    private String host;
    @Value("${spring.mail.username}")
    private String username;


    @Override
   @Async
    public void SendConfirmationEmail(String name, String to, String token) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject("NEW VERIFICATION ACCOUNT");
            message.setFrom(username);
            message.setTo(to);
            message.setText(EmailUtils.getEmailMessage(name, host, token));
            javaMailSender.send(message);
        }catch (Exception e){
        log.error(e.getMessage());
        throw new RuntimeException("error happens while sending email");
        }
    }
}
