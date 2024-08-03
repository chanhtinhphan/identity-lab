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
public class UserCreationRequest {
    @Size(min = 3, message = "INVALID_USERNAME")
    String username;
    @Size(min = 6, message = "INVALID_PASSWORD")
    String password;
    String firstname;
    String lastname;
    @DobConstraint(min = 1,message = "INVALID_DOB")
    LocalDate dob;
}
