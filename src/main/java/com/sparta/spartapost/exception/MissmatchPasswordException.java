package com.sparta.spartapost.exception;

public class MissmatchPasswordException extends IllegalArgumentException{
    public MissmatchPasswordException(){
        super("비밀번호가 일치하지 않습니다");
    }
}
