package com.myweb.controller;


import com.myweb.dto.ApiResponse;
import com.myweb.dto.UserCreateDTO;
import com.myweb.dto.UserDTO;
import com.myweb.entity.User;
import com.myweb.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    public UserService userService;

    //get all users
    @GetMapping("")
    ApiResponse<List<UserDTO>> getAllUser(){
        List<UserDTO> userDTOList = userService.getAllUsersDTO();
        return ApiResponse.<List<UserDTO>>builder()
                .success(true)
                .result(userDTOList)
                .build() ;
    }

    //get user by id
    @GetMapping("/{userId}")
    ApiResponse<UserDTO> getUserById(@PathVariable Long userId) {
        return ApiResponse.<UserDTO>builder()
                .success(true)
                .result(userService.getUserDTOById(userId))
                .build();
    }

    @PostMapping("/new-user")
    ApiResponse<User> createUser(@RequestBody @Valid UserCreateDTO userCreateDTO){
        return ApiResponse.<User>builder()
                .success(true)
                .result(userService.createUser(userCreateDTO))
                .build();
    }

    @PutMapping("update/{userId}")
    ApiResponse<UserDTO> updateUser(@PathVariable Long userId, @RequestBody @Valid UserDTO userDto){

        return ApiResponse.<UserDTO>builder()
                .success(true)
                .result(userService.updateUser(userId,userDto))
                .message("Update successful!")
                .build();
    }



    @DeleteMapping("{userId}")
    ApiResponse<User> delete(@PathVariable Long userId){
        userService.deleteUser(userId);
        return ApiResponse.<User>builder()
                .success(true)
                .message("Delete user successful")
                .build();
    }
}
