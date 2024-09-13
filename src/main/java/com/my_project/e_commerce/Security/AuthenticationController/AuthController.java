package com.my_project.e_commerce.Security.AuthenticationController;



import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService Authservice;

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(Authservice.register(registerRequest));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse>authenticate(
            @RequestBody AuthenticationRequest request)  {
        return ResponseEntity.ok(Authservice.authenticate(request));
    }

    @PostMapping("/refresh-token")
    public void refresh_token(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Authservice.refresh_token(request,response);
    }
    @GetMapping
    public ResponseEntity<?>ActiveAccount(@RequestParam("token") String token){
      return ResponseEntity.ok(Authservice.Active_Account(token));
    }

}