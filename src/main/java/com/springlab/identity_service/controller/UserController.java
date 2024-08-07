package com.springlab.identity_service.controller;

import com.springlab.identity_service.dto.PaginationObject;
import com.springlab.identity_service.dto.request.PasswordCreationRequest;
import com.springlab.identity_service.dto.request.UserCreationRequest;
import com.springlab.identity_service.dto.request.UserUpdateRequest;
import com.springlab.identity_service.dto.response.ApiResponse;
import com.springlab.identity_service.dto.response.UserResponse;
import com.springlab.identity_service.entity.User;
import com.springlab.identity_service.exception.AppException;
import com.springlab.identity_service.exception.ErrorCode;
import com.springlab.identity_service.mapper.UserMapper;
import com.springlab.identity_service.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;
    UserMapper userMapper;
    Map<String, String> propertyMap = Map.of(
            "first_name", "firstname",
            "last_name", "lastname",
            "username", "username",
            "dob", "dob",
            "email", "email"
    );

    @GetMapping("/page")
    ApiResponse<PaginationObject<UserResponse>> listPageUser(
            @RequestParam(value = "pageNum", required = false, defaultValue = "1")
            @Min(value = 1) Integer pageNum,
            @RequestParam(value = "size", required = false, defaultValue = "2")
            @Min(value = 2) @Max(value = 20) Integer pageSize,
            @RequestParam(value = "sort", required = false, defaultValue = "first_name") String sortFeild
    ) {

        if (!propertyMap.containsKey(sortFeild)) {
            throw new AppException(ErrorCode.INVALID_SORT_FIELD);
        }
        Page<User> pageInfo = userService.listByPage(pageNum - 1, pageSize, propertyMap.get(sortFeild));
        List<UserResponse> result = pageInfo.getContent().stream()
                .map(userMapper::toUserResponse).toList();
        PaginationObject<UserResponse> paginationObject = PaginationObject.<UserResponse>builder()
                .pageNum(pageInfo.getNumber() + 1)
                .pageSize(pageInfo.getSize())
                .totalPages(pageInfo.getTotalPages())
                .totalElements(pageInfo.getTotalElements())
                .data(result)
                .build();
        return ApiResponse.<PaginationObject<UserResponse>>builder()
                .result(paginationObject)
                .build();
    }

    @PostMapping
    ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.createUser(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<UserResponse>> getUsers() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ApiResponse.<List<UserResponse>>builder()
                .result(userService.getUsers())
                .build();
    }

    @GetMapping("/{id}")
    ApiResponse<UserResponse> getUser(@PathVariable("id") String id) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.getUser(id))
                .build();
    }

    @GetMapping("/my-info")
    ApiResponse<UserResponse> getMyInfo() {
        return ApiResponse.<UserResponse>builder()
                .result(userService.getMyInfo())
                .build();
    }

    @PutMapping("{id}")
    ApiResponse<UserResponse> updateUser(@PathVariable String id, @RequestBody UserUpdateRequest request) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.updateUser(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<String> deteleUser(@PathVariable String id) {
        userService.deleteUser(id);
        return ApiResponse.<String>builder()
                .result("User has been deleted")
                .build();
    }

    @PostMapping("/create-password")
    ApiResponse<Void> createUser(@RequestBody @Valid PasswordCreationRequest request) {
        userService.createPassword(request);
        return ApiResponse.<Void>builder()
                .message("Password has been created, you could use it to login")
                .build();
    }

}
