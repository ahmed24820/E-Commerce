package com.my_project.e_commerce.Security.AuthenticationController;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.my_project.e_commerce.Dtos.userDto;
import com.my_project.e_commerce.Email.Repo.ConfirmationRepo;
import com.my_project.e_commerce.Email.Service.EmailServiceImp;
import com.my_project.e_commerce.Email.Utils.EmailConfirmation;
import com.my_project.e_commerce.Exceptions.DuplicatedError;
import com.my_project.e_commerce.Mapper.UserMapper;
import com.my_project.e_commerce.Models.RoleEnum;
import com.my_project.e_commerce.Models.User;
import com.my_project.e_commerce.Repos.RoleRepo;
import com.my_project.e_commerce.Repos.UserRepo;
import com.my_project.e_commerce.Security.CustomFilter.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@AllArgsConstructor
public class AuthService {
    private  final UserRepo userRepo;
    private final JwtService jwTservice;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RoleRepo repo;

    private final ConfirmationRepo confirmationRepo;
    private final EmailServiceImp emailServiceImp;

    public ResponseEntity<?> register(RegisterRequest registerRequest) {
        Optional <User> optional = userRepo.findByUsername(registerRequest.getUsername());
         if (optional.isPresent()){
             throw new DuplicatedError("user already exists");
         }
         else {
         var user = User.builder()
                 .firstname(registerRequest.getFirstname())
                 .lastname(registerRequest.getLastname())
                 .address(registerRequest.getAddress())
                 .password(bCryptPasswordEncoder.encode(registerRequest.getPassword()))
                 .username(registerRequest.getUsername())
                 .phoneNumber(registerRequest.getPhoneNumber())
                 .roles(List.of(repo.findByName(RoleEnum.valueOf(registerRequest.getRole())).orElseThrow()))
                 .build();
            user.setActive(false);
            userRepo.save(user);

             EmailConfirmation confirmation = new EmailConfirmation(user);
             confirmationRepo.save(confirmation);

            emailServiceImp.SendConfirmationEmail(user.getFirstname(),user.getUsername(),confirmation.getConfirmationToken());

             return ResponseEntity.ok("Verify email by the link sent on your email address");
    }
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request)  {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                ));
        User user=userRepo.findByUsername(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("not found"));
        var generatedToken=jwTservice.GenerateToken(user);
        var refresh_token = jwTservice.Create_Refresh_Token(user);
        log.info("the Auth token is >> {}" , generatedToken);
        return AuthenticationResponse.builder()
                .access_token(generatedToken)
                .Refresh_token(refresh_token)
                .build();
    }

    public void refresh_token(
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {

        final String requestHeader=request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refresh_token;
        final String username;
        if(requestHeader == null || !requestHeader.startsWith("Bearer ")){
            return;
        }
        refresh_token=requestHeader.substring(7);
        username=jwTservice.ExtractUsername(refresh_token);
        if (username != null) {
            UserDetails userDetails = this.userRepo.findByUsername(username).orElseThrow();
            if (jwTservice.valid(refresh_token, userDetails)) {
                var access = jwTservice.GenerateToken(userDetails);
                var auth_response = AuthenticationResponse.builder()
                        .access_token(access)
                        .Refresh_token(refresh_token)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), auth_response);
            }
        }
      }
      public String Active_Account(String token){
        EmailConfirmation confirmationToken = confirmationRepo.findByConfirmationToken(token).orElseThrow();
       User user = userRepo.findByUsername(confirmationToken.getUser().getUsername()).orElseThrow();
       user.setActive(true);
       userRepo.save(user);
       return "congrats " + user.getFirstname() + "\t u have been be an active member now ";
    }

       }
