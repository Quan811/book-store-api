package com.myweb.controller;

import com.myweb.dto.ApiResponse;
import com.myweb.dto.AuthenticateDTO;
import com.myweb.dto.LoginDTO;
import com.myweb.dto.UserDTO;
import com.myweb.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthenticationService authenticationService;
    @PostMapping("")
    public ApiResponse<UserDTO> login(@RequestBody LoginDTO loginDto){
        UserDTO userDTO = authenticationService.authenticate(loginDto);

        return ApiResponse.<UserDTO>builder()
                .success(true)
                .result(userDTO)
                .build();
    }

}
