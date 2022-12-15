package com.sparta.spartapost.service;

import com.sparta.spartapost.dto.LoginRequestDto;
import com.sparta.spartapost.dto.SignupRequestDto;
import com.sparta.spartapost.entity.User;
import com.sparta.spartapost.exception.OverlapUserExistException;
import com.sparta.spartapost.exception.UserNotExistException;
import com.sparta.spartapost.jwt.JwtUtil;
import com.sparta.spartapost.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public void signup(SignupRequestDto signupRequestDto) {
        //회원 중복 확인 by username
        Optional<User> overlapUser = userRepository.findByUsername(signupRequestDto.getUsername());
        if(overlapUser.isPresent()) throw new OverlapUserExistException();
        User user = new User(signupRequestDto);
        userRepository.save(user);
    }

    @Transactional
    public void login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        User user = userRepository.findByUsername(loginRequestDto.getUsername()).orElseThrow(UserNotExistException::new);
        user.validatePassword(loginRequestDto.getPassword());
        response.addHeader(jwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUsername()));
    }
}
