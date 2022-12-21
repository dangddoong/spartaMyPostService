package com.sparta.spartapost.dto;

import com.sparta.spartapost.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private final Long commentId;
    private final Long postId;
    private final String contents;
    private final String username;
    private final LocalDateTime createdAt;

    public CommentResponseDto(Comment comment){
        this.commentId = comment.getCommentId();
        this.postId = comment.getPost().getId();
        this.contents = comment.getContents();
        this.username = comment.getContentsWriter();
        this.createdAt = comment.getCreatedAt();
    }
}
