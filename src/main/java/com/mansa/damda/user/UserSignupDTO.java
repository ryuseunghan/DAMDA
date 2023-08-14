package com.mansa.damda.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class UserSignupDTO {
    private String userName;

    private String password;

    private String phoneNumber;

    private Long marketId;
}
