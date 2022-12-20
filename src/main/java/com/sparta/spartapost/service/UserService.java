package com.sparta.spartapost.service;

import com.sparta.spartapost.dto.LoginRequestDto;
import com.sparta.spartapost.dto.SignupRequestDto;
import com.sparta.spartapost.entity.User;
import com.sparta.spartapost.entity.UserRoleEnum;
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
    private static final String ADMIN_TOKEN = "AAABaeGoPuGoJolLiDa";
    private final JwtUtil jwtUtil;


    @Transactional
    public void signup(SignupRequestDto signupRequestDto) {
        //회원 중복 확인 by username
        Optional<User> overlapUser = userRepository.findByUsername(signupRequestDto.getUsername());
        if(overlapUser.isPresent()) throw new OverlapUserExistException();
        UserRoleEnum role = UserRoleEnum.USER;
        if(!signupRequestDto.getAdminToken().isEmpty()) {
            if (!signupRequestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRoleEnum.ADMIN;
        }
        userRepository.save(new User(signupRequestDto, role));
    }

    @Transactional
    public String login(LoginRequestDto loginRequestDto) {
        User user = userRepository.findByUsername(loginRequestDto.getUsername()).orElseThrow(UserNotExistException::new);
        user.validatePassword(loginRequestDto.getPassword());
        return jwtUtil.createToken(user.getUsername(), user.getRole());
    }
}
