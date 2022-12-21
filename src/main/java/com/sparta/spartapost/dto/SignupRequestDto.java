package com.sparta.spartapost.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
//@Setter  클론 코딩 상에서는 붙어있긴 하지만, 가급적 안쓰려고 노력해봅시다.
public class SignupRequestDto {
    @Size(min = 4, max = 10,message = "username Size error")
    @Pattern(regexp = "^[a-z0-9]*$",message = "username Pattern error")
    private String username;

    @Size(min = 8, max = 15,message = "password Size error")
    @Pattern(regexp ="^[a-zA-Z0-9`~!@#$%^&*()-_=+]*$", message = "password Pattern error")
    private String password;
    private String adminToken="";

}
