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
//    @Column(nullable = false)
//    private Long postId;
    @Column(nullable = false)
    private String contents;
    @Column(nullable = false)
    private String contentsWriter;
    @ManyToOne
    @JoinColumn(name = "Post_id", nullable = false)
    private Post post;

    public Comment(CommentRequestDto commentRequestDto, Post post, String contentsWriter){
        this.post = post;
        this.contentsWriter = post.getUser().getUsername();
        this.contents = commentRequestDto.getContents();
        post.getCommentList().add(this);
    }

    public void validateUsername(String username) {
        if(!username.equals(this.getContentsWriter())) throw new IllegalArgumentException("작성자명 불일치");
    }

    public void updateComment(CommentRequestDto commentRequestDto) {
        this.contents = commentRequestDto.getContents();
    }
}
