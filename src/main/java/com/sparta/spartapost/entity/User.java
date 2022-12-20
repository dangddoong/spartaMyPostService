package com.sparta.spartapost.entity;

import com.sparta.spartapost.dto.SignupRequestDto;
import com.sparta.spartapost.exception.MissmatchPasswordException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity(name = "users")
public class User {
    @Id
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role ;

    public User(SignupRequestDto signupRequestDto, UserRoleEnum role) {
        this.username = signupRequestDto.getUsername();
        this.password = signupRequestDto.getPassword();
        this.role = role;
    }
    public void validatePassword(String password) {
        if (!password.equals(this.getPassword())) throw new MissmatchPasswordException();
    }
}
