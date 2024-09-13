package com.my_project.e_commerce.API;

import com.my_project.e_commerce.Exceptions.NotFounded;
import com.my_project.e_commerce.Mapper.UserMapper;
import com.my_project.e_commerce.Models.User;
import com.my_project.e_commerce.Repos.UserRepo;
import com.my_project.e_commerce.Service.Implementation.UserServiceImp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
@Tag(name = "ADMIN")
public class AdminApi {
    private final UserServiceImp userServiceImp;
    private final UserMapper userMapper;

    @Operation(
            description = "this api help the admin to get all users in our database",
            summary = "retrieving all users in database",
            responses = {
                    @ApiResponse(description = "success",
                            responseCode = "200",
                            content =@Content(mediaType = "application/json")
                    ),
                    @ApiResponse(description = "UN AUTHORIZED / JWT EXPIRED",
                            responseCode = "403",
                            content =@Content(mediaType = "application/json")
                    ),
                    @ApiResponse(description = "AN INTERNAL ERROR",
                            responseCode = "501",
                            content =@Content(mediaType = "application/json")
                    )

            }
    )
     @GetMapping("/all-users")
    public ResponseEntity<?>AllUsers(){
        return ResponseEntity.ok(userServiceImp.findAll());
    }

    @Operation(
            description = "this api help the admin to get user with id",
            summary = "retrieving  user by {id}",
            responses = {
                    @ApiResponse(description = "success",
                            responseCode = "200",
                            content =@Content(mediaType = "application/json")
                    ),
                    @ApiResponse(description = "UN AUTHORIZED / JWT EXPIRED",
                            responseCode = "403",
                            content =@Content(mediaType = "application/json")
                    ),
                    @ApiResponse(description = "AN INTERNAL ERROR",
                            responseCode = "501",
                            content =@Content(mediaType = "application/json")
                    )

            }
    )

    @GetMapping("/user/{id}")
    public ResponseEntity<?>findUserById(@PathVariable("id")long id){
       return ResponseEntity.ok(userServiceImp.getById(id));
    }
    @Operation(
            description = "this api help the admin to get user with email",
            summary = "retrieving  user by {email}",
            responses = {
                    @ApiResponse(description = "success",
                            responseCode = "200",
                            content =@Content(mediaType = "application/json")
                    ),
                    @ApiResponse(description = "UN AUTHORIZED / JWT EXPIRED",
                            responseCode = "403",
                            content =@Content(mediaType = "application/json")
                    ),
                    @ApiResponse(description = "AN INTERNAL ERROR",
                            responseCode = "501",
                            content =@Content(mediaType = "application/json")
                    )

            }
    )

    @GetMapping("/user/{email}")
    public ResponseEntity<?>findUserByEmail(@PathVariable("email")String email) throws NotFounded{
        if (userServiceImp.findByUsername(email)!=null) {
            return ResponseEntity.ok(userMapper.map(userServiceImp.findByUsername(email)));
       } else {
            throw new NotFounded("the user not founded");
        }
     }
    @Operation(
            description = "this api help the admin to show his profile",
            responses = {
                    @ApiResponse(description = "success",
                            responseCode = "200",
                            content =@Content(mediaType = "application/json")
                    ),
                    @ApiResponse(description = "UN AUTHORIZED / JWT EXPIRED",
                            responseCode = "403",
                            content =@Content(mediaType = "application/json")
                    ),
                    @ApiResponse(description = "AN INTERNAL ERROR",
                            responseCode = "501",
                            content =@Content(mediaType = "application/json")
                    )

            }
    )

    @GetMapping("/profile")
    public ResponseEntity<?>profile(){
        var user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(userMapper.map(user));
    }


}
