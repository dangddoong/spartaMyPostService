package com.sparta.spartapost.entity;

import com.sparta.spartapost.dto.PostRequestDto;
import com.sparta.spartapost.dto.PostResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Post extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String contents;
    public Post(PostRequestDto postRequestDto, String username){
        this.title = postRequestDto.getTitle();
        this.username = username;
        this.contents = postRequestDto.getContents();
    }

//    public void updatePost(PostRequestDto postRequestDto) {
//        this.title = postRequestDto.getTitle();
//        this.username = postRequestDto.getUsername();
//        this.contents = postRequestDto.getContents();
//    }
    private PostResponseDto convertToPostResponseDto(Post post){
        return new PostResponseDto(post);
    }

//    public void validatePassword(String password) {
//        if (!password.equals(this.getPassword())) throw new IllegalArgumentException("비밀번호 불일치");
//    }

}
