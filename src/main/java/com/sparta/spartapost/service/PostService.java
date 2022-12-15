package com.sparta.spartapost.service;

import com.sparta.spartapost.dto.PostRequestDto;
import com.sparta.spartapost.dto.PostResponseDto;
import com.sparta.spartapost.entity.Post;
import com.sparta.spartapost.exception.PostNotExistException;
import com.sparta.spartapost.jwt.JwtUtil;
import com.sparta.spartapost.repository.PostRepository;
import com.sparta.spartapost.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import io.jsonwebtoken.Claims;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    //    public boolean pwIsValid(String inputPw, String postPw){
//        return inputPw.equals(postPw);
//    }
    public void tokenNullCheck(String token) {
        if (token == null) throw new IllegalArgumentException("토큰이 텅텅 비었어요");
    }

    @Transactional
    public PostResponseDto createPost(PostRequestDto postRequestDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        String username;
        tokenNullCheck(token);
        if(jwtUtil.validateToken(token)){
            username = jwtUtil.getUserInfoFromToken(token).getSubject();
        }else throw new IllegalArgumentException("Token Error");

        Post post = new Post(postRequestDto,username);
        postRepository.save(post);
        return new PostResponseDto(post);
    }

    @Transactional(readOnly = true)
    public List<PostResponseDto> getAllPosts() {
        return postRepository.findAllByOrderByModifiedAtDesc().stream()
                .map(PostResponseDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PostResponseDto getPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(PostNotExistException::new);
        return new PostResponseDto(post);
    }

    @Transactional
    public PostResponseDto updatePost(Long id, PostRequestDto postRequestDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        String username;
        tokenNullCheck(token);
        if(jwtUtil.validateToken(token)){
            username = jwtUtil.getUserInfoFromToken(token).getSubject();
        }else throw new IllegalArgumentException("Token Error");

        Post post = postRepository.findById(id).orElseThrow(PostNotExistException::new);
        post.validateUsername(username);
        post.updatePost(postRequestDto);
        postRepository.save(post);
        return new PostResponseDto(post);
//        } return new PostResponseDto();//TODO 비밀번호가 다를 때 리턴값 잡아줘야합니다.
    }

//    @Transactional
//    public void deletePost(Long id, String pw) {
//        Post post = postRepository.findById(id).orElseThrow(PostNotExistException::new);
//        post.validatePassword(pw);
//        postRepository.deleteById(id);
//    }
}
