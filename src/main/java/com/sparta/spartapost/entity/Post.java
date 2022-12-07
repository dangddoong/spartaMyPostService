package com.sparta.spartapost.entity;

import com.sparta.spartapost.dto.PostRequestDto;
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
    private String password;
    @Column(nullable = false)
    private String contents;
    public Post(PostRequestDto postRequestDto){
        this.title = postRequestDto.getTitle();
        this.username = postRequestDto.getUsername();
        this.password = postRequestDto.getPassword();
        this.contents = postRequestDto.getContents();
    }

    public void updatePost(PostRequestDto postRequestDto) {
        this.title = postRequestDto.getTitle();
        this.username = postRequestDto.getUsername();
        this.contents = postRequestDto.getContents();
    }
}
