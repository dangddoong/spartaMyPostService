package com.sparta.spartapost.exception;

public class PostNotExistException extends IllegalArgumentException{
    public PostNotExistException(){
        super("게시글이 존재하지 않습니다");
    }
}
