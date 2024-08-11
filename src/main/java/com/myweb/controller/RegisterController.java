package com.myweb.controller;

import com.myweb.dto.ApiResponse;
import com.myweb.dto.UserCreateDTO;
import com.myweb.dto.UserDTO;
import com.myweb.entity.User;
import com.myweb.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private UserService userService;

    @PostMapping("")
    ApiResponse<User> register(@RequestBody @Valid UserCreateDTO userCreateDTO){

        userService.createUser(userCreateDTO);
        return ApiResponse.<User>builder()
                .success(true)
                .message("Register successful")
                .build();
    }

}
