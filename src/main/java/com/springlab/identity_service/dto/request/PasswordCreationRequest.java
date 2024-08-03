package com.springlab.identity_service.dto.request;

import com.springlab.identity_service.validator.DobConstraint;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PasswordCreationRequest {
    @Size(min = 6, message = "INVALID_PASSWORD")
    String password;

}
