package com.sparta.spartapost.service;

import com.sparta.spartapost.dto.PostRequestDto;
import com.sparta.spartapost.dto.PostResponseDto;
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
    public boolean pwIsValid(String inputPw, String postPw){
        return inputPw.equals(postPw);
    }
    public PostResponseDto createPost(PostRequestDto postRequestDto) {
        Post post = new Post(postRequestDto);
        postRepository.save(post);
        return new PostResponseDto(post);
    }
    @Transactional(readOnly = true)
    public List<Post> getAllPosts(){
        return postRepository.findAllByOrderByModifiedAtDesc();
    }
    @Transactional(readOnly = true)
    public Post getPost(Long id) {
        return postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시물이 존재하지 않습니다.")
        );
    }
    @Transactional
    public Long updatePost(Long id, PostRequestDto postRequestDto){
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시물이 존재하지 않습니다.")
        );
        if(pwIsValid(postRequestDto.getPassword(), post.getPassword())){
        post.updatePost(postRequestDto);
        return post.getId();
    } return 1L;//TODO 나중에 리턴값 DTO로 바꿔줘야함.
    }
    @Transactional
    public String deletePost(Long id, String pw) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시물이 존재하지 않습니다.")
        );
        if(pwIsValid(pw, post.getPassword())){
            postRepository.deleteById(id);
            return "삭제에 성공했습니다.";
        } return "비밀번호가 다릅니다. 삭제 실패";
    }
}
