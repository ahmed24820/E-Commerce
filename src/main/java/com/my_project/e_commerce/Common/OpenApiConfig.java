package com.my_project.e_commerce.Common;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info =
              @Info(
              contact = @Contact(
              name = "Ahmed Ibrahim",
              email = "az01064452071@gmail.com"
       ),
       description = "OpenApi documentation for Spring boot E-Commerce project",
       title = "project Resources",
       version = "2.3",
       license = @License(
        name = "Licence name",
        url = "https://some-url.com"
       ),
       termsOfService = "Terms of service"
        ),
              servers = {
                     @Server(
                 description = "Local ENV",
                     url = "http://localhost:8080"
                 )
          },
         security = {
         @SecurityRequirement(name = "bearerAuth")
         }
         )

        @SecurityScheme(
               name = "bearerAuth",
               description = "JWT DESCRIPTION",
               scheme = "bearer",
               type = SecuritySchemeType.HTTP,
               bearerFormat = "JWT",
               in = SecuritySchemeIn.HEADER
       )


public class OpenApiConfig {

}
