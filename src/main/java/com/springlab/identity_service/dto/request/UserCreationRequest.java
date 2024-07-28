package com.springlab.identity_service.dto.request;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class UserCreationRequest {
    @Size(min = 3,message = "INVALID_USERNAME")
    private String username;
    @Size(min = 6,message = "INVALID_PASSWORD")
    private String password;
    private String firstname;
    private String lastname;
    private LocalDate dob;
}
