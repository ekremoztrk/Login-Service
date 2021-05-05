package com.parksmartbackend.parksmart.services;

import com.parksmartbackend.parksmart.model.User;
import com.parksmartbackend.parksmart.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public User findByEmail(String email){
        return userRepository.findByEmail(email).orElse(null);
    }

}
