package com.tecsup.app.micro.user.mapper;

import com.tecsup.app.micro.user.dto.User;
import com.tecsup.app.micro.user.entity.UserEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-07T20:30:08-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.15 (Homebrew)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toDomain(UserEntity entity) {
        if ( entity == null ) {
            return null;
        }

        User user = new User();

        user.setId( entity.getId() );
        user.setName( entity.getName() );
        user.setEmail( entity.getEmail() );
        user.setPhone( entity.getPhone() );
        user.setAddress( entity.getAddress() );

        return user;
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
}
