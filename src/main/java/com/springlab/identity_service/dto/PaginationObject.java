package com.springlab.identity_service.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class PaginationObject<T> {
    int pageNum;
    int pageSize;
    int totalPages;
    long totalElements;
    List<T> data;
}
