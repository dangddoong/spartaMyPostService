package com.sparta.spartapost.service;

import com.sparta.spartapost.dto.LoginRequestDto;
import com.sparta.spartapost.dto.SignupRequestDto;
import com.sparta.spartapost.entity.User;
import com.sparta.spartapost.exception.OverlapUserExistException;
import com.sparta.spartapost.exception.UserNotExistException;
import com.sparta.spartapost.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public void signup(SignupRequestDto signupRequestDto) {
        //회원 중복 확인 by username
        Optional<User> overlapUser = userRepository.findByUsername(signupRequestDto.getUsername());
        overlapUser.orElseThrow(OverlapUserExistException::new);
        User user = new User(signupRequestDto);
        userRepository.save(user);
    }

    @Transactional
    public void login(LoginRequestDto loginRequestDto) {
        User user = userRepository.findByUsername(loginRequestDto.getUsername()).orElseThrow(UserNotExistException::new);
        user.validatePassword(loginRequestDto.getPassword());
    }
}
