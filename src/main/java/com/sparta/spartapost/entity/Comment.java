package com.sparta.spartapost.entity;

import com.sparta.spartapost.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Comment extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long commentId;
    @Column(nullable = false)
    private Long postId;
    @Column(nullable = false)
    private String contents;
    @Column(nullable = false)
    private String username;

    public Comment(CommentRequestDto commentRequestDto, Long postId, String username){
        this.postId = postId;
        this.username = username;
        this.contents = commentRequestDto.getContents();
    }

    public void validateUsername(String username) {
        if(!username.equals(this.getUsername())) throw new IllegalArgumentException("작성자명 불일치");
    }

    public void updateComment(CommentRequestDto commentRequestDto) {
        this.contents = commentRequestDto.getContents();
    }
}
