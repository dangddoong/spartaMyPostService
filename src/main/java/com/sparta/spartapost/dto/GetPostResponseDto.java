package com.sparta.spartapost.dto;

import com.sparta.spartapost.entity.Comment;
import com.sparta.spartapost.entity.Post;
import com.sparta.spartapost.repository.CommentRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class GetPostResponseDto {
    private Long id;
    private final String title;
    private final String username;
    private final String contents;
    private final LocalDateTime createdAt;
    private final List<CommentResponseDto> commentResponseDtoList;


    public GetPostResponseDto(Post post, List<Comment> commentList){
        this.id = post.getId();
        this.title = post.getTitle();
        this.username = post.getUser().getUsername();
        this.contents = post.getContents();
        this.createdAt = post.getCreatedAt();
//        this.commentResponseDtoList = CommentRepository.findAllByPostIdOrderByModifiedAtDesc(post.getId()).stream().sorted(Comparator.comparing(Comment::getModifiedAt).reversed())
//                .map(CommentResponseDto::new).collect(Collectors.toList());
    }
//    public PostResponseDto(){
//        this.contents = "비밀번호가 다릅니다";
//    }
}
