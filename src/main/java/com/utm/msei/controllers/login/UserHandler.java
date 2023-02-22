package com.utm.msei.controllers.login;

import com.utm.msei.persistence.dto.UserDto;

public class UserHandler {

    private UserDto user;

    public void setUser(UserDto userDto) {
        this.user = userDto;
    }

    public UserDto getUser() {
        return this.user;
    }
}
