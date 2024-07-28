package com.springlab.identity_service.service;

import com.springlab.identity_service.dto.request.UserCreationRequest;
import com.springlab.identity_service.dto.request.UserUpdateRequest;
import com.springlab.identity_service.entity.User;
import com.springlab.identity_service.exception.AppException;
import com.springlab.identity_service.exception.ErrorCode;
import com.springlab.identity_service.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(UserCreationRequest request) {
        if (userRepository.existsByUsername(request.getUsername()))
            throw new RuntimeException("user existed");
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setDob(request.getDob());
        return userRepository.save(user);
    }

    public List<User> getUser() {
        return userRepository.findAll();
    }

    public User getUser(String id) {
        return userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_EXISTED));
    }

    public User updateUser(String id, UserUpdateRequest request) {
        User user = getUser(id);
        user.setPassword(request.getPassword());
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setDob(request.getDob());
        return userRepository.save(user);
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}
