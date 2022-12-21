package com.sparta.spartapost.service;

import com.sparta.spartapost.dto.PostRequestDto;
import com.sparta.spartapost.dto.GetPostResponseDto;
import com.sparta.spartapost.dto.PostResponseDto;
import com.sparta.spartapost.entity.Post;
import com.sparta.spartapost.entity.User;
import com.sparta.spartapost.exception.PostNotExistException;
import com.sparta.spartapost.exception.UserNotExistException;
import com.sparta.spartapost.jwt.JwtUtil;
import com.sparta.spartapost.repository.PostRepository;
import com.sparta.spartapost.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public PostResponseDto createPost(PostRequestDto postRequestDto, String username) {
        User user = userRepository.findByUsername(username).orElseThrow(UserNotExistException::new);
        Post post = new Post(postRequestDto, user);
        postRepository.saveAndFlush(post);
        return new PostResponseDto(post);
    }

    @Transactional(readOnly = true)
    public List<GetPostResponseDto> getAllPosts() {
        return postRepository.findAllByOrderByModifiedAtDesc().stream()
                .map(GetPostResponseDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public GetPostResponseDto getPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(PostNotExistException::new);
        return new GetPostResponseDto(post);
    }

    @Transactional
    public PostResponseDto userUpdatePost(Long id, PostRequestDto postRequestDto, String username) {
        Post post = postRepository.findById(id).orElseThrow(PostNotExistException::new);
        post.validateUsername(username);
        post.updatePost(postRequestDto);
        postRepository.save(post);
        return new PostResponseDto(post);
    }

    @Transactional
    public PostResponseDto adminUpdatePost(Long id, PostRequestDto postRequestDto) {
        Post post = postRepository.findById(id).orElseThrow(PostNotExistException::new);
        post.updatePost(postRequestDto);
        postRepository.save(post);
        return new PostResponseDto(post);
    }

    @Transactional
    public void userDeletePost(Long id, String username) {
        Post post = postRepository.findById(id).orElseThrow(PostNotExistException::new);
        post.validateUsername(username);
        postRepository.deleteById(id);
    }

    @Transactional
    public void adminDeletePost(Long id) {
        if(!postRepository.existsById(id)) throw new PostNotExistException();
        postRepository.deleteById(id);
    }
}
