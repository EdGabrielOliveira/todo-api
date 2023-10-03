package com.example.todoapi.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegisterUserDto {
    @NotBlank
    @Size(max = 30)
    String name;
    @Email
    String email;
    @NotBlank
    String password;
}