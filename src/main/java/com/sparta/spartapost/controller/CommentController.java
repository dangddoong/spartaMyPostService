package com.sparta.spartapost.controller;

import com.sparta.spartapost.dto.CommentRequestDto;
import com.sparta.spartapost.dto.CommentResponseDto;
import com.sparta.spartapost.dto.PostRequestDto;
import com.sparta.spartapost.dto.PostResponseDto;
import com.sparta.spartapost.jwt.JwtUtil;
import com.sparta.spartapost.service.CommentService;
import com.sparta.spartapost.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class CommentContoller {
    private final CommentService commentService;
    private final JwtUtil jwtUtil;

    public void tokenNullCheck(String token) {
        if (token == null) throw new IllegalArgumentException("토큰이 텅텅 비었어요");
    }

    @PostMapping("/api/comments/{postId}")
    public CommentResponseDto createComment(@PathVariable Long postId, @RequestBody CommentRequestDto commentRequestDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        tokenNullCheck(token);
        if (!jwtUtil.validateToken(token)) throw new IllegalArgumentException("Token Error");
        String username = jwtUtil.getUserInfoFromToken(token).getSubject();
        return commentService.createComment(postId, CommentRequestDto username);
    }

    @PutMapping("/api/comments/{commentId}")
    public CommentResponseDto userUpdateComment(@PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        tokenNullCheck(token);
        if (!jwtUtil.validateToken(token)) throw new IllegalArgumentException("Token Error");
        String role = jwtUtil.getUserInfoFromToken(token).get(JwtUtil.AUTHORIZATION_KEY).toString();
        if (!role.equals("USER")) throw new IllegalArgumentException("Wrong Approach");
        String username = jwtUtil.getUserInfoFromToken(token).getSubject();
        return commentService.userUpdateComment(commentId, commentRequestDto, username);
    }
    @PutMapping("/api/admin/comments/{commentId}")
    public CommentResponseDto adminUpdateComment(@PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        tokenNullCheck(token);
        if (!jwtUtil.validateToken(token)) throw new IllegalArgumentException("Token Error");
        String role = jwtUtil.getUserInfoFromToken(token).get(JwtUtil.AUTHORIZATION_KEY).toString();
        if (!role.equals("ADMIN")) throw new IllegalArgumentException("Wrong Approach");
        return commentService.adminUpdateComment(commentId, commentRequestDto);
    }
    @DeleteMapping("/api/comments/{commentId}")
    public ResponseEntity<String> userDeleteComment(@PathVariable Long commentId, HttpServletRequest request){
        String token = jwtUtil.resolveToken(request);
        tokenNullCheck(token);
        if (!jwtUtil.validateToken(token)) throw new IllegalArgumentException("Token Error");
        String role = jwtUtil.getUserInfoFromToken(token).get(JwtUtil.AUTHORIZATION_KEY).toString();
        if (!role.equals("USER")) throw new IllegalArgumentException("Wrong Approach");
        String username = jwtUtil.getUserInfoFromToken(token).getSubject();
        commentService.userDeleteComment(commentId, username);
        return new ResponseEntity<>("댓글 삭제완료", HttpStatus.OK);
    }
    @DeleteMapping("/api/admin/comments/{commentId}")
    public ResponseEntity<String> userDeleteComment(@PathVariable Long commentId, HttpServletRequest request){
        String token = jwtUtil.resolveToken(request);
        tokenNullCheck(token);
        if (!jwtUtil.validateToken(token)) throw new IllegalArgumentException("Token Error");
        String role = jwtUtil.getUserInfoFromToken(token).get(JwtUtil.AUTHORIZATION_KEY).toString();
        if (!role.equals("ADMIN")) throw new IllegalArgumentException("Wrong Approach");
        commentService.adminDeleteComment(commentId);
        return new ResponseEntity<>("댓글 삭제완료", HttpStatus.OK);
    }
}
