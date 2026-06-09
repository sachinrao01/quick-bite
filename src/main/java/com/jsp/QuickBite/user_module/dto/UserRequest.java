package com.jsp.QuickBite.user_module.dto;

import lombok.Data;

@Data
public class UserRequest {
    private String name;
    private String email;
    private Long phone;
    private String password;
}
