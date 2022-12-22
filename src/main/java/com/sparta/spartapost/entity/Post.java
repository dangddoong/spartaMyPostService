package com.sparta.spartapost.entity;

import com.sparta.spartapost.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Post extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Post_id")
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String contents;
    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "USER_USERNAME", nullable = false)
    private User user;

    String username;

//    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
//    List<Comment> commentList = new ArrayList<>();

    public Post(PostRequestDto postRequestDto, User user) {
        this.title = postRequestDto.getTitle();
        this.user = user;
        this.contents = postRequestDto.getContents();
    }


    public void validateUsername(String username) {
        if (!username.equals(this.user.getUsername())) throw new IllegalArgumentException("작성자명 불일치");
    }

    public void updatePost(PostRequestDto postRequestDto) {
        this.title = postRequestDto.getTitle();
        this.contents = postRequestDto.getContents();
    }
//    private PostResponseDto convertToPostResponseDto(Post post){
//        return new PostResponseDto(post);
//    }
//    public void validatePassword(String password) {
//        if (!password.equals(this.getPassword())) throw new IllegalArgumentException("비밀번호 불일치");
//    }

}
