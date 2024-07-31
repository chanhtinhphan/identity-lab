package com.springlab.identity_service.mapper;

import com.springlab.identity_service.dto.request.PermissionRequest;
import com.springlab.identity_service.dto.response.PermissionResponse;
import com.springlab.identity_service.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionRespone(Permission permission);
}
