package com.letscode.userapp.dto;

import com.letscode.userapp.document.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class UserResponse extends UserRequest{
    private String id;
    private String name;
    private String address;
    private String email;

    public static UserResponse toResponde(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setAddress(user.getAddress());
        response.setEmail(user.getEmail());
        return response;
    }
}
