package com.sparta.spartapost.dto;

import com.sparta.spartapost.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponseDto {
    private final Long postId;
    private final String title;
    private final String username;
    private final String contents;
    private final LocalDateTime createdAt;

    public PostResponseDto(Post post) {
        this.postId = post.getId();
        this.title = post.getTitle();
        this.username = post.getUser().getUsername();
        this.contents = post.getContents();
        this.createdAt = post.getCreatedAt();
    }
}

