package com.springlab.identity_service.controller;

import com.springlab.identity_service.dto.request.RoleRequest;
import com.springlab.identity_service.dto.response.ApiResponse;
import com.springlab.identity_service.dto.response.RoleResponse;
import com.springlab.identity_service.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/roles")
public class RoleController {
    RoleService roleService;


    @PostMapping
    ApiResponse<RoleResponse> create(@RequestBody RoleRequest request) {
        return ApiResponse.<RoleResponse>builder()
                .result(roleService.create(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<RoleResponse>> getAll() {
        return ApiResponse.<List<RoleResponse>>builder()
                .result(roleService.getAll())
                .build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<Void> delete(@PathVariable("id") String id) {
        roleService.delete(id);
        return ApiResponse.<Void>builder().build();
    }

}

