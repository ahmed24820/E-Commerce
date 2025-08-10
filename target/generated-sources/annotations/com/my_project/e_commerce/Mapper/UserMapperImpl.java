package com.my_project.e_commerce.Mapper;

import com.my_project.e_commerce.Dtos.userDto;
import com.my_project.e_commerce.Models.User;
import com.my_project.e_commerce.Models.User.UserBuilder;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 3.42.50.v20250729-0351, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public userDto map(User user) {
        if ( user == null ) {
            return null;
        }

        userDto userDto = new userDto();

        userDto.setFirstname( user.getFirstname() );
        userDto.setId( user.getId() );
        userDto.setLastname( user.getLastname() );
        userDto.setUsername( user.getUsername() );

        return userDto;
    }

    @Override
    public User unmap(userDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        UserBuilder user = User.builder();

        user.firstname( userDto.getFirstname() );
        user.id( userDto.getId() );
        user.lastname( userDto.getLastname() );
        user.username( userDto.getUsername() );

        return user.build();
    }
}
