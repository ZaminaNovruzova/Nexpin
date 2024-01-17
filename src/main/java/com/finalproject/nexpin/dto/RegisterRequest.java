package com.finalproject.nexpin.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {
    String fullName;
    private String username;

    private String password;
}