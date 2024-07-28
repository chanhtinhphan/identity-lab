package com.springlab.identity_service.service;

import com.springlab.identity_service.dto.request.UserCreationRequest;
import com.springlab.identity_service.dto.request.UserUpdateRequest;
import com.springlab.identity_service.dto.response.UserResponse;
import com.springlab.identity_service.entity.User;
import com.springlab.identity_service.exception.AppException;
import com.springlab.identity_service.exception.ErrorCode;
import com.springlab.identity_service.mapper.UserMapper;
import com.springlab.identity_service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class UserService {
     UserRepository userRepository;
     UserMapper userMapper;

    public User createUser(UserCreationRequest request) {
        if (userRepository.existsByUsername(request.getUsername()))
            throw new RuntimeException("user existed");
        User user = userMapper.toUser(request);
        return userRepository.save(user);
    }

    public List<User> getUser() {
        return userRepository.findAll();
    }

    public UserResponse getUser(String id) {
        return userMapper.toUserResponse(userRepository.findById(id).
                orElseThrow(() -> new AppException(ErrorCode.USER_EXISTED)));
    }

    public UserResponse updateUser(String id, UserUpdateRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_EXISTED));
        userMapper.updateUser(user, request);

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}
