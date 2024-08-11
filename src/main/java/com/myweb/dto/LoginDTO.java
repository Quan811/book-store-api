package com.myweb.dto;

import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginDTO {
    @Size(min = 3, max = 16, message = "Username must be from 3 to 16 characters")
    String username;

    @Size(min = 8, max = 16, message = "Password must be from 8 to 16 characters")
    String password;
}
