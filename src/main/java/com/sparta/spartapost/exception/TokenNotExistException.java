package com.sparta.spartapost.exception;

public class TokenNotExistException extends IllegalArgumentException{
    public TokenNotExistException(){super("토큰이 텅텅 비어있어요");}
}
