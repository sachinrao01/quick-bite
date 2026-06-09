package com.jsp.QuickBite.user_module.service.impl;
import com.jsp.QuickBite.user_module.dao.UserRepository;
import com.jsp.QuickBite.user_module.dto.LoginRequest;
import com.jsp.QuickBite.user_module.dto.UserRequest;
import com.jsp.QuickBite.user_module.dto.UserResponse;
import com.jsp.QuickBite.user_module.exception.UserException;
import com.jsp.QuickBite.user_module.model.Role;
import com.jsp.QuickBite.user_module.model.User;
import com.jsp.QuickBite.user_module.service.UserService;
import jdk.jshell.spi.ExecutionControl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

//    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }  // this all work will done by @RequiredArgsConstructor annotation of lombok so we can remove this constructor and use @Autowired on class level or on constructor level

//    @Autowired
//    public UserServiceImpl(UserRepository userRepository){
//        this.userRepository=userRepository;
//    }

    @Override
    public UserResponse register(UserRequest userRequest) {
        if(userRepository.existsByEmail(userRequest.getEmail())){
            throw new UserException("email is already exist");
        }
        User user = new User();
        user.setEmail(userRequest.getEmail());
        user.setName(userRequest.getName());
        user.setPhone(userRequest.getPhone());
        user.setPassword(userRequest.getPassword());
//        passwordEncoder.encode(userRequest.getPassword()) this is for encrypting password but for now we are not using it

        user.setRole(Role.CUSTOMER);

//        SAVE TO DATABASE
        User savedUser = userRepository.save(user);

//        store in user Response
//        UserResponse response = new UserResponse(savedUser);// if we will not make constructor in user response then we have to set data one by one
        UserResponse response = new UserResponse();

        response.setUser_id(savedUser.getId());
        response.setName(savedUser.getName());
        response.setEmail(savedUser.getEmail());
        response.setPhone(savedUser.getPhone());

        return response;
    }

    @Override
    public UserResponse login(LoginRequest login) {
        User user = userRepository.findByEmail(login.getEmail()).orElseThrow(()-> new UserException("invalid email or password"));
        if(! user.getPassword().equals(login.getPassword())){
            throw new UserException("invalid email or password");
        }
        if(user !=null){
            UserResponse response = new UserResponse();
            response.setUser_id(user.getId());
            response.setName(user.getName());
            response.setEmail(user.getEmail());
            response.setPhone(user.getPhone());
            return response;
        }
        return null;

    }

    @Override
    public UserResponse profile(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserException("user not found with id: " + id));
        if(user != null) {
            UserResponse response = new UserResponse();
            response.setUser_id(user.getId());
            response.setName(user.getName());
            response.setEmail(user.getEmail());
            response.setPhone(user.getPhone());
            return response;
        }
        return null;
    }

    @Override
    public void deleteUser(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserException("user not found with id: " + id));
        user.setActive(false);
        userRepository.save(user);
    }

    @Override
    public UserResponse changePassword(String email, String oldPassword, String newPassword) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserException("user not found with email: " + email));
        if (!user.getPassword().equals(oldPassword)) {
            throw new UserException("old password is incorrect");
        }
        user.setPassword(newPassword);
        User updatedUser = userRepository.save(user);

        UserResponse response = new UserResponse();
        response.setUser_id(updatedUser.getId());
        response.setName(updatedUser.getName());
        response.setEmail(updatedUser.getEmail());
        response.setPhone(updatedUser.getPhone());

        return response;
    }

}