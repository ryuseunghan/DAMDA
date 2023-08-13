package com.mansa.damda.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class UserSignupDTO {
    private String userName;

    private String email;

    private String password;

    private String userType;

    private Long marketId;
}
