package com.lambdacode.spring.boot.crud.User;

import lombok.Data;

@Data
public class UserDTO {
    private Integer userId;
    private String username;
    private String email;
    private String password;
    private String userType;
}
