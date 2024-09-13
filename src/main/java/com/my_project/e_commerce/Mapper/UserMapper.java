package com.my_project.e_commerce.Mapper;

import com.my_project.e_commerce.Dtos.userDto;
import com.my_project.e_commerce.Models.User;
import org.mapstruct.Mapper;

@Mapper()
public interface UserMapper {
    userDto map(User user);
    User unmap(userDto userDto);

}
