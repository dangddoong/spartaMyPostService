package com.sparta.spartapost.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
//@Setter  클론 코딩 상에서는 붙어있긴 하지만, 가급적 안쓰려고 노력해봅시다.
public class SignupRequestDto {
    @Size(min = 4, max = 10)
    @Pattern(regexp = "[a-z0-9]",message = "최소 4자 이상, 10자 이하이며 알파벳 소문자와 숫자만 가능합니다")
    private String username;
    @Pattern(regexp ="[a-zA-Z0-9]", message = "최소 8자 이상, 15자 이하이며 알파벳 대소문자와 숫자만 가능합니다")
    @Size(min = 8, max = 15)
    private String password;

}
