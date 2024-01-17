package com.finalproject.nexpin.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
