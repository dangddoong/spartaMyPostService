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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;
//    @Column(nullable = false)
//    private Long postId;
    @Column(nullable = false)
    private String contents;
    @Column(nullable = false)
    private String contentsWriter;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Post_id", nullable = false)
    private Post post;

    public Comment(CommentRequestDto commentRequestDto, Post post, String contentsWriter){
        this.post = post;
        this.contentsWriter = contentsWriter;
        this.contents = commentRequestDto.getContents();
//        post.getCommentList().add(this); //이거는 세터랑 다름없다.
    }

    public void validateUsername(String username) {
        if(!username.equals(this.getContentsWriter())) throw new IllegalArgumentException("작성자명 불일치");
    }

    public void updateComment(CommentRequestDto commentRequestDto) {
        this.contents = commentRequestDto.getContents();
    }
}
