package com.sparta.spartapost.exception;

public class CommentNotExistException extends IllegalArgumentException{
    public CommentNotExistException(){super("댓글이 존재하지 않습니다");}
}
