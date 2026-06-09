package com.jsp.QuickBite.user_module.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
