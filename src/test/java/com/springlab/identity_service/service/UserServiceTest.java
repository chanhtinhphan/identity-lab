package com.springlab.identity_service.service;

import com.springlab.identity_service.dto.request.UserCreationRequest;
import com.springlab.identity_service.dto.response.UserResponse;
import com.springlab.identity_service.entity.User;
import com.springlab.identity_service.exception.AppException;
import com.springlab.identity_service.repository.RoleRepository;
import com.springlab.identity_service.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestPropertySource("/test.properties")
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;
    @MockBean
    RoleRepository roleRepository;

    private UserCreationRequest request;
    private UserResponse userResponse;
    private User user;
    private LocalDate dob;

    @BeforeEach
    void initData() {
        dob = LocalDate.of(1990, 1, 1);
        request = UserCreationRequest.builder()
                .username("john")
                .firstname("john")
                .lastname("due")
                .password("123456")
                .dob(dob)
                .build();

        userResponse = UserResponse.builder()
                .id("w437rweNcr&sdFserf")
                .username("john")
                .firstname("john")
                .lastname("due")
                .dob(dob)
                .build();

        user = User.builder()
                .id("w437rweNcr&sdFserf")
                .username("john")
                .firstname("john")
                .lastname("due")
                .dob(dob)
                .build();
    }

    @Test
    void createUser_validRequest_success() {
        //GIVEN
        when(userRepository.save(any())).thenReturn(user);
        when(roleRepository.findById(anyString())).thenReturn( Optional.empty());

        //WHEN
        var response = userService.createUser(request);

        //THEN
        Assertions.assertThat(response.getId()).isEqualTo("w437rweNcr&sdFserf");
        Assertions.assertThat(response.getUsername()).isEqualTo("john");
    }

    @Test
    void createUser_userExisted_fail() {
        //GIVEN
        when(userRepository.save(any())).thenThrow(DataIntegrityViolationException.class);

        //WHEN
        var exception = assertThrows(AppException.class, () -> userService.createUser(request));

        //THEN
        Assertions.assertThat(exception.getErrorCode().getCode()).isEqualTo(1002);
    }
}
