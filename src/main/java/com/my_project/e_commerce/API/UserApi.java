package com.my_project.e_commerce.API;

import com.my_project.e_commerce.Common.apiResponse;
import com.my_project.e_commerce.Common.ChangePasswordRequest;
import com.my_project.e_commerce.Exceptions.NullPointerException;
import com.my_project.e_commerce.Mapper.UserMapper;
import com.my_project.e_commerce.Models.User;
import com.my_project.e_commerce.Service.Implementation.UserServiceImp;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/me")
@RequiredArgsConstructor
@Tag(name = "ME")
public class UserApi {
    private final UserServiceImp userService;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping()
    public ResponseEntity<?>me(){
        User user =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(new apiResponse("found successfully",userMapper.map(user)));
    }

    @PostMapping("/change-Password")
    public ResponseEntity<?>changePassword(@RequestBody ChangePasswordRequest request) throws NullPointerException {
        User user =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
           try {
            if (bCryptPasswordEncoder.matches(request.getOldPassword(),user.getPassword())){
             if (request.getNewPassword().equals(request.getRepeatNewPassword())){
                user.setPassword(bCryptPasswordEncoder.encode(request.getNewPassword()));
                userService.Save(user);
             }else {
                 throw new NullPointerException("can not change the password the new password is null");
             }
            }
           }catch (Exception e){
               return ResponseEntity.ok(new apiResponse("not changed the passwords do not match",e.getMessage()));
           }
        return ResponseEntity.ok(new apiResponse("changed successfully",null));
    }

}
