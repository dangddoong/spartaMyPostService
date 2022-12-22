package com.sparta.spartapost;

import com.sparta.spartapost.exception.CommentNotExistException;
import com.sparta.spartapost.exception.MissmatchPasswordException;
import com.sparta.spartapost.exception.OverlapUserExistException;
import com.sparta.spartapost.exception.PostNotExistException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleCustomException(CommentNotExistException e1, MissmatchPasswordException e2, OverlapUserExistException e3, PostNotExistException e4)
}
