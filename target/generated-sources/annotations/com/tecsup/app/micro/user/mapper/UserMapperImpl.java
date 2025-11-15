package com.tecsup.app.micro.user.mapper;

import com.tecsup.app.micro.user.dto.User;
import com.tecsup.app.micro.user.dto.UserRequest;
import com.tecsup.app.micro.user.dto.UserResponse;
import com.tecsup.app.micro.user.entity.UserEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-14T21:24:17-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.14 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toDomain(UserEntity entity) {
        if ( entity == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.id( entity.getId() );
        user.name( entity.getName() );
        user.email( entity.getEmail() );
        user.phone( entity.getPhone() );
        user.address( entity.getAddress() );

        return user.build();
    }

    @Override
    public UserEntity toEntity(User domain) {
        if ( domain == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setId( domain.getId() );
        userEntity.setName( domain.getName() );
        userEntity.setEmail( domain.getEmail() );
        userEntity.setPhone( domain.getPhone() );
        userEntity.setAddress( domain.getAddress() );

        return userEntity;
    }

    @Override
    public List<User> toDomain(List<UserEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( entities.size() );
        for ( UserEntity userEntity : entities ) {
            list.add( toDomain( userEntity ) );
        }

        return list;
    }

    @Override
    public User toDomain(UserRequest request) {
        if ( request == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.name( request.getName() );
        user.email( request.getEmail() );
        user.phone( request.getPhone() );
        user.address( request.getAddress() );

        return user.build();
    }

    @Override
    public UserResponse toResponse(User createUser) {
        if ( createUser == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        String email = null;
        String phone = null;
        String address = null;

        id = createUser.getId();
        name = createUser.getName();
        email = createUser.getEmail();
        phone = createUser.getPhone();
        address = createUser.getAddress();

        UserResponse userResponse = new UserResponse( id, name, email, phone, address );

        return userResponse;
    }
}
