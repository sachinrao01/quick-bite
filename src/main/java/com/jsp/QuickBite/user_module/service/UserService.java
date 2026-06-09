package com.jsp.QuickBite.user_module.service;

import com.jsp.QuickBite.user_module.dto.LoginRequest;
import com.jsp.QuickBite.user_module.dto.UserRequest;
import com.jsp.QuickBite.user_module.dto.UserResponse;
import org.springframework.stereotype.Service;

public interface UserService {
    UserResponse register(UserRequest user);
    UserResponse login(LoginRequest login);
    UserResponse profile(Integer id);
    void deleteUser(Integer id);
    UserResponse changePassword(String email, String oldPassword, String newPassword);
}
