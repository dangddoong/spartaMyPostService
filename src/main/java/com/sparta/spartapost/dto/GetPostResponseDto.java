package com.sparta.spartapost.dto;

import com.sparta.spartapost.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class GetPostResponseDto {
    private Long id;
    private final String title;
    private final String username;
    private final String contents;
    private final LocalDateTime createdAt;
    private final List<CommentResponseDto> commentResponseDtoList;


    public GetPostResponseDto(Post post, List<CommentResponseDto> commentResponseDtoList ){
        this.id = post.getId();
        this.title = post.getTitle();
        this.username = post.getUsername();
        this.contents = post.getContents();
        this.createdAt = post.getCreatedAt();
        this.commentResponseDtoList = commentResponseDtoList;
    }
//    public PostResponseDto(){
//        this.contents = "비밀번호가 다릅니다";
//    }
}
