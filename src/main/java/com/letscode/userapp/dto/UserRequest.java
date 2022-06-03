package com.letscode.userapp.dto;

import lombok.Data;

@Data
public class UserRequest {
    private String name;
    private String password;
    private String address;
    private String email;

}
