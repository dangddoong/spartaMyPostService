package com.sparta.spartapost.controller;

import com.sparta.spartapost.dto.CommentRequestDto;
import com.sparta.spartapost.dto.CommentResponseDto;
import com.sparta.spartapost.exception.MissmatchRoleException;
import com.sparta.spartapost.exception.TokenNotExistException;
import com.sparta.spartapost.jwt.JwtUtil;
import com.sparta.spartapost.service.CommentService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.message.AuthException;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final JwtUtil jwtUtil;

    public void tokenNullCheck(String token) {
        if (token == null) throw new TokenNotExistException();
    }

    @PostMapping("/api/comments/{postId}")
    public CommentResponseDto createComment(@PathVariable Long postId, @RequestBody CommentRequestDto commentRequestDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        tokenNullCheck(token);
        String username = jwtUtil.getUserInfoFromToken(token).getSubject();
        return commentService.createComment(postId, commentRequestDto, username);
    }

    @PutMapping("/api/comments/{commentId}")
    public CommentResponseDto userUpdateComment(@PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto, HttpServletRequest request)  {
        String token = jwtUtil.resolveToken(request);
        tokenNullCheck(token);
        Claims claims = jwtUtil.getUserInfoFromToken(token);

        String role = claims.get(JwtUtil.AUTHORIZATION_KEY).toString();
        if (!role.equals("USER")) throw new MissmatchRoleException();
        String username = claims.getSubject();
        return commentService.userUpdateComment(commentId, commentRequestDto, username);
    }
    @PutMapping("/api/admin/comments/{commentId}")
    public CommentResponseDto adminUpdateComment(@PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        tokenNullCheck(token);
        String role = jwtUtil.getUserInfoFromToken(token).get(JwtUtil.AUTHORIZATION_KEY).toString();
        if (!role.equals("ADMIN")) throw new MissmatchRoleException();
        return commentService.adminUpdateComment(commentId, commentRequestDto);
    }
    @DeleteMapping("/api/comments/{commentId}")
    public ResponseEntity<String> userDeleteComment(@PathVariable Long commentId, HttpServletRequest request){
        String token = jwtUtil.resolveToken(request);
        tokenNullCheck(token);
        String role = jwtUtil.getUserInfoFromToken(token).get(JwtUtil.AUTHORIZATION_KEY).toString();
        if (!role.equals("USER")) throw new MissmatchRoleException();
        String username = jwtUtil.getUserInfoFromToken(token).getSubject();
        commentService.userDeleteComment(commentId, username);
        return new ResponseEntity<>("댓글 삭제완료", HttpStatus.OK);
    }
    @DeleteMapping("/api/admin/comments/{commentId}")
    public ResponseEntity<String> adminDeleteComment(@PathVariable Long commentId, HttpServletRequest request){
        String token = jwtUtil.resolveToken(request);
        tokenNullCheck(token);
        String role = jwtUtil.getUserInfoFromToken(token).get(JwtUtil.AUTHORIZATION_KEY).toString();
        if (!role.equals("ADMIN")) throw new MissmatchRoleException();
        commentService.adminDeleteComment(commentId);
        return new ResponseEntity<>("댓글 삭제완료", HttpStatus.OK);
    }
}
