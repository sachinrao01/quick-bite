package com.jsp.QuickBite.user_module.dto;

import com.jsp.QuickBite.user_module.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponse {
    private Integer user_id;
    private String name;
    private String email;
    private Long phone;

        public UserResponse(User user) {
            this.user_id = user.getId();
            this.name = user.getName();
            this.email = user.getEmail();
            this.phone = user.getPhone();
        }

}
