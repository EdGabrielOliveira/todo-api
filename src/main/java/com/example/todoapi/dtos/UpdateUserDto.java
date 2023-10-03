package com.example.todoapi.dtos;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdateUserDto {
    @Nullable
    @Size(max = 30)
    String name;

    @Nullable
    @Email
    String email;
}