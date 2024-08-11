package com.myweb.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreateDTO {
    Long userId;

    @NotBlank(message = "Username is required")
    @Size(min = 3, message = "Username must be at least 3 characters long")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Username must not contain special characters")
    String username;

    @NotBlank(message = "Password is required")
    @Size(min = 3, message = "Password must be at least 6 characters long")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Password must not contain special characters")
    String password;

    @Email(message = "Invalid email format")
    String email;

    @NotBlank(message = "Full name is required")
    @Size(min = 3, message = "Full name must be at least 3 characters long")
    String fullName;

    @Size(min = 10, max = 10, message = "Phone number must contain exactly 10 digits")
    @Pattern(regexp = "^\\d+$", message = "Phone number must contain only digits")
    String phoneNumber;

    @NotBlank(message = "Address is not be blank")
    @Size(min = 5, message = "Address is minimum 5 character")
    String address;

    //    @AgeConstrain(minAge = 18, message = "You must be 18 or older to use our service")
    @NotNull(message = "Date of birth is required")
    LocalDate dob;
}
