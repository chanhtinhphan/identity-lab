package com.springlab.identity_service.dto.request;

import com.springlab.identity_service.validator.DobConstraint;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {
    String password;
    String firstname;
    String lastname;
    @DobConstraint(min = 2,message = "INVALID_DOB")
    LocalDate dob;
    List<String> roles;
}
