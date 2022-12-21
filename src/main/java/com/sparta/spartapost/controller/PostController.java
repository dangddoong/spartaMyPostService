package com.sparta.spartapost.controller;

import com.sparta.spartapost.dto.PostRequestDto;
import com.sparta.spartapost.dto.GetPostResponseDto;
import com.sparta.spartapost.dto.PostResponseDto;
import com.sparta.spartapost.jwt.JwtUtil;
import com.sparta.spartapost.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final JwtUtil jwtUtil;

    public void tokenNullCheck(String token) {
        if (token == null) throw new IllegalArgumentException("토큰이 텅텅 비었어요");
    }

    @GetMapping("/")
    public ModelAndView home() {
        return new ModelAndView("index");
    }

    @PostMapping("/api/posts")
    public PostResponseDto createPost(@RequestBody PostRequestDto postRequestDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        tokenNullCheck(token);
        if (!jwtUtil.validateToken(token)) throw new IllegalArgumentException("Token Error");
        String username = jwtUtil.getUserInfoFromToken(token).getSubject();
        return postService.createPost(postRequestDto, username);
    }

    @GetMapping("/api/posts")
    public List<GetPostResponseDto> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/api/posts/{id}")
    public GetPostResponseDto getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }

    @PutMapping("/api/posts/{id}")
    public PostResponseDto userUpdatePost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        tokenNullCheck(token);
        if (!jwtUtil.validateToken(token)) throw new IllegalArgumentException("Token Error");
        String role = jwtUtil.getUserInfoFromToken(token).get(JwtUtil.AUTHORIZATION_KEY).toString();
        if(!role.equals("USER")) throw new IllegalArgumentException("Wrong Approach");
        String username = jwtUtil.getUserInfoFromToken(token).getSubject();
        return postService.userUpdatePost(id, postRequestDto, username);
    }

    @PutMapping("/api/admin/posts/{id}")
    public PostResponseDto adminUpdatePost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        tokenNullCheck(token);
        if (!jwtUtil.validateToken(token)) throw new IllegalArgumentException("Token Error");
        String role = jwtUtil.getUserInfoFromToken(token).get(JwtUtil.AUTHORIZATION_KEY).toString();
        if(!role.equals("ADMIN")) throw new IllegalArgumentException("Wrong Approach");
        return postService.adminUpdatePost(id, postRequestDto);
    }

    @DeleteMapping("/api/posts/{id}")
    public ResponseEntity<String> userDeletePost(@PathVariable Long id, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        tokenNullCheck(token);
        if (!jwtUtil.validateToken(token)) throw new IllegalArgumentException("Token Error");
        String role = jwtUtil.getUserInfoFromToken(token).get(JwtUtil.AUTHORIZATION_KEY).toString();
        if(!role.equals("USER")) throw new IllegalArgumentException("Wrong Approach");
        String username = jwtUtil.getUserInfoFromToken(token).getSubject();
        postService.userDeletePost(id, username);
        return new ResponseEntity<>("게시물 삭제완료", HttpStatus.OK);
    }
    @DeleteMapping("/api/admin/posts/{id}")
    public ResponseEntity<String> adminDeletePost(@PathVariable Long id, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        tokenNullCheck(token);
        if (!jwtUtil.validateToken(token)) throw new IllegalArgumentException("Token Error");
        String role = jwtUtil.getUserInfoFromToken(token).get(JwtUtil.AUTHORIZATION_KEY).toString();
        if(!role.equals("ADMIN")) throw new IllegalArgumentException("Wrong Approach");
        postService.adminDeletePost(id);
        return new ResponseEntity<>("게시물 삭제완료", HttpStatus.OK);
    }
}
