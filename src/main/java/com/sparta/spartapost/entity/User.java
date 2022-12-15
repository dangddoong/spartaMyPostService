package com.sparta.spartapost.entity;

import com.sparta.spartapost.dto.SignupRequestDto;
import com.sparta.spartapost.exception.MissmatchPasswordException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity(name = "users")
public class User {
    @Id
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    public User(SignupRequestDto signupRequestDto) {
        this.username = signupRequestDto.getUsername();
        this.password = signupRequestDto.getPassword();
    }
    public void validatePassword(String password) {
        if (!password.equals(this.getPassword())) throw new MissmatchPasswordException();
    }
}
