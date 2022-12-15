package com.sparta.spartapost.dto;

import com.sparta.spartapost.entity.Post;
import lombok.Getter;

@Getter
public class PostRequestDto {
    private String title;
    private String contents;

//    public PostRequestDto(Post post){
//        this.title = post.getTitle();
//        this.password = post.getPassword();
//        this.username = post.getUsername();
//        this.contents = post.getContents();
//    }
}
