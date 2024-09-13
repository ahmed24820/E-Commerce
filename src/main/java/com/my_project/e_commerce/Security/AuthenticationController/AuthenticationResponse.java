package com.my_project.e_commerce.Security.AuthenticationController;

import lombok.*;


@Builder
@Setter
@Getter
@AllArgsConstructor
public class AuthenticationResponse {
    private String access_token;
    private String Refresh_token;
}
