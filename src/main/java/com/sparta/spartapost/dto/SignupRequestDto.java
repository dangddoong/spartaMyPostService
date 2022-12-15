package com.sparta.spartapost.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
//@Setter  클론 코딩 상에서는 붙어있긴 하지만, 가급적 안쓰려고 노력해봅시다.
public class SignupRequestDto {
    private final String username;
    private final String password;

    public SignupRequestDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
