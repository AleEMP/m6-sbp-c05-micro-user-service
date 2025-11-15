package com.tecsup.app.micro.user.service;

import com.tecsup.app.micro.user.dto.User;
import com.tecsup.app.micro.user.entity.UserEntity;
import com.tecsup.app.micro.user.mapper.UserMapper;
import com.tecsup.app.micro.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class UserService {
    @Autowired
    private final UserRepository userRepository;
    private final UserMapper mapper;


    public User getUserById(Long id){

        UserEntity entity = userRepository.findById(id).orElse(null);

        return  mapper.toDomain(entity);
    }

    public List<User> getAllUsers() {

        List<UserEntity> entities = userRepository.findAll();
        return this.mapper.toDomain(entities);

    }
    @Transactional
    public boolean deleteUser(Long id) {
        Optional<UserEntity> userOptional = userRepository.findById(id);

        if (userOptional.isEmpty()) {
            return false;
        }
        userRepository.deleteById(id);
        return true;
    }

    @Transactional
    public User createUser(User user){
        validateUserInput(user);
        UserEntity entity = this.mapper.toEntity(user);
        UserEntity ent = userRepository.save(entity);
        return this.mapper.toDomain(userRepository.save(entity));
    }

    private void validateUserInput(User newUser) {
         if (!newUser.hasValidEmail())
            throw new RuntimeException("Invalid user data: Invalid email");
    }
}
