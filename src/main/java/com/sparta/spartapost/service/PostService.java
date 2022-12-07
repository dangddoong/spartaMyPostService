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
    public Post createPost(PostRequestDto postRequestDto) {
        Post post = new Post(postRequestDto);
        postRepository.save(post);
        return post;
    }
    @Transactional(readOnly = true)
    public List<Post> getAllPosts(){
        return postRepository.findAllByOrderByModifiedAtDesc();
    }
}
