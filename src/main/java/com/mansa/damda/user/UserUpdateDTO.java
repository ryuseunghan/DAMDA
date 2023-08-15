package com.mansa.damda.user;

import lombok.Data;

@Data
public class UserUpdateDTO {
    private String userNickName;

    private String userName;

    private String password;

    private String phoneNumber;

    private Long marketId;
}
