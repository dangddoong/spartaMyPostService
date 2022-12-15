package com.sparta.spartapost.exception;

public class UserNotExistException extends IllegalArgumentException{
    public UserNotExistException(){
        super("등록된 사용자가 없습니다");
    }
}
