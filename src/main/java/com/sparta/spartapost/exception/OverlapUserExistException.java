package com.sparta.spartapost.exception;

public class OverlapUserExistException extends IllegalArgumentException{
    public OverlapUserExistException(){
        super("중복된 사용자가 존재합니다.");
    }
}
