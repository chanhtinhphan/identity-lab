package com.springlab.identity_service.controller;

import com.springlab.identity_service.dto.request.UserCreationRequest;
import com.springlab.identity_service.dto.request.UserUpdateRequest;
import com.springlab.identity_service.dto.response.ApiResponse;
import com.springlab.identity_service.entity.User;
import com.springlab.identity_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    ApiResponse<User> creareUser(@RequestBody @Valid UserCreationRequest request) {
        ApiResponse<User> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.createUser(request));
        return apiResponse;
    }

    @GetMapping
    List<User> getUser() {
        return userService.getUser();
    }

    @GetMapping("/{id}")
    User getUser(@PathVariable("id") String id) {
        return userService.getUser(id);
    }

    @PutMapping("{id}")
    User updateUser(@PathVariable String id, @RequestBody UserUpdateRequest request) {
        return userService.updateUser(id, request);
    }

    @DeleteMapping("/{id}")
    String deteleUser(@PathVariable String id) {
        userService.deleteUser(id);
        return "user has been deleted";
    }
}
