package com.myweb.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDTO {

    Long userId;

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
