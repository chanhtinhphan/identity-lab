package com.springlab.identity_service.service;

import com.springlab.identity_service.dto.request.PermissionRequest;
import com.springlab.identity_service.dto.response.PermissionResponse;
import com.springlab.identity_service.entity.Permission;
import com.springlab.identity_service.mapper.PermissionMapper;
import com.springlab.identity_service.repository.PermissionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class PermissionService {
    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    public PermissionResponse create(PermissionRequest request) {
        Permission permission = permissionMapper.toPermission(request);
        permission = permissionRepository.save(permission);
        return permissionMapper.toPermissionRespone(permission);
    }

    public List<PermissionResponse> getAll() {
        List<Permission> permissions = permissionRepository.findAll();
        return permissions.stream()
                .map(permission -> permissionMapper.toPermissionRespone(permission))
                .toList();
    }

    public void delete(String permission) {
        permissionRepository.deleteById(permission);
    }
}
