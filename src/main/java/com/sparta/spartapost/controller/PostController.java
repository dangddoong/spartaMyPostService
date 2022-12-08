package com.sparta.spartapost.controller;

import com.sparta.spartapost.dto.PostRequestDto;
import com.sparta.spartapost.dto.PostResponseDto;
import com.sparta.spartapost.entity.Post;
import com.sparta.spartapost.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    @GetMapping("/")
    public ModelAndView home(){
        return new ModelAndView("index");
    }
    @PostMapping("/api/post")
    public PostResponseDto createPost(@RequestBody PostRequestDto postRequestDto){
        return postService.createPost(postRequestDto);
    }
    @GetMapping("/api/posts")
    public List<PostResponseDto> getAllPosts(){
        return postService.getAllPosts();
    }
    @GetMapping("/api/post/{id}")
    public PostResponseDto getPost(@PathVariable Long id){
        return postService.getPost(id);
    }
    @PutMapping("/api/post/{id}")
    public PostResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto){
        return postService.updatePost(id, postRequestDto);
    }
    //TODO 비밀번호를 어떤 어노테이션으로 입력받을지 수정해야됨(url에 비밀번호 노출이 안되도록 PostDeleteRequestDto를 하나 만들어줘야되나...)
    @DeleteMapping("/api/post/{id}/{pw}")
    public void deletePost(@PathVariable Long id, @PathVariable String pw) {
        postService.deletePost(id, pw);
    }

}
