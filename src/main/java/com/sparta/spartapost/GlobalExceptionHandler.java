package com.sparta.spartapost;

import com.sparta.spartapost.exception.*;
import io.jsonwebtoken.ClaimJwtException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SecurityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.security.auth.message.AuthException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(TokenNotExistException.class)
    protected ResponseEntity<String> handleTokenNotExistException(TokenNotExistException e) {
        return new ResponseEntity<>("토큰이 유효하지 않습니다.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler ({AuthException.class})
    protected ResponseEntity<String> handleJwtException(AuthException e) {
        return new ResponseEntity<>("토큰이 유효하지 않습니다.", HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(OverlapUserExistException.class)
    protected ResponseEntity<String> handleOverlapUserExistException(OverlapUserExistException e) {
        return new ResponseEntity<>("중복된 username 입니다.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({UserNotExistException.class, MissmatchPasswordException.class})
    protected ResponseEntity<String> handleLoginException(IllegalArgumentException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(MissmatchRoleException.class)
    protected ResponseEntity<String> handleMissMatchRoleException(MissmatchRoleException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.FORBIDDEN);
    }
    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<String> handleEtcException(RuntimeException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }


}
