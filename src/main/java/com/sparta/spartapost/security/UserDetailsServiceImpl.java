package com.sparta.spartapost.security;

import com.sparta.spartapost.entity.User;
import com.sparta.spartapost.exception.UserNotExistException;
import com.sparta.spartapost.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(UserNotExistException::new);

        return new UserDetailsImpl(user, user.getUsername());
    }

}