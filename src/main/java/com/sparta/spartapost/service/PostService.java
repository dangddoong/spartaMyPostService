package com.sparta.spartapost.service;

import com.sparta.spartapost.dto.PostRequestDto;
import com.sparta.spartapost.entity.Post;
import com.sparta.spartapost.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    public boolean pwIsValid(PostRequestDto postRequestDto, Post post){ return postRequestDto.getPassword().equals(post.getPassword());}
    public Post createPost(PostRequestDto postRequestDto) {
        Post post = new Post(postRequestDto);
        postRepository.save(post);
        return post;
    }
    @Transactional(readOnly = true)
    public List<Post> getAllPosts(){
        return postRepository.findAllByOrderByModifiedAtDesc();
    }
    @Transactional
    public Long updatePost(Long id, PostRequestDto postRequestDto){
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        if(pwIsValid(postRequestDto, post)){
        post.updatePost(postRequestDto);
        return post.getId();
    }

    public Long deletePost(Long id) {

    }
}
