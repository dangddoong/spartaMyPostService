package com.sparta.spartapost.service;

import com.sparta.spartapost.dto.CommentRequestDto;
import com.sparta.spartapost.dto.CommentResponseDto;
import com.sparta.spartapost.entity.Post;
import com.sparta.spartapost.exception.CommentNotExistException;
import com.sparta.spartapost.exception.PostNotExistException;
import com.sparta.spartapost.repository.CommentRepository;
import com.sparta.spartapost.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sparta.spartapost.entity.Comment;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Transactional
    public CommentResponseDto createComment(Long postId, CommentRequestDto commentRequestDto, String username) {
        Post post = postRepository.findById(postId).orElseThrow(PostNotExistException::new);
        Comment comment = new Comment(commentRequestDto, post, username);
        commentRepository.save(comment);
        return new CommentResponseDto(comment);
    }

    @Transactional
    public CommentResponseDto userUpdateComment(Long commentId, CommentRequestDto commentRequestDto, String username){
        Comment comment = commentRepository.findById(commentId).orElseThrow(CommentNotExistException::new);
        comment.validateUsername(username);
        comment.updateComment(commentRequestDto);
        commentRepository.save(comment);
        return new CommentResponseDto(comment);
    }
    @Transactional
    public CommentResponseDto adminUpdateComment(Long commentId, CommentRequestDto commentRequestDto){
        Comment comment = commentRepository.findById(commentId).orElseThrow(CommentNotExistException::new);
        comment.updateComment(commentRequestDto);
        commentRepository.save(comment);
        return new CommentResponseDto(comment);
    }

    @Transactional
    public void userDeleteComment(Long commentId, String username){
        Comment comment = commentRepository.findById(commentId).orElseThrow(CommentNotExistException::new);
        comment.validateUsername(username);
        commentRepository.deleteById(commentId);
    }

    @Transactional
    public void adminDeleteComment(Long commentId){
        if(!commentRepository.existsById(commentId)) throw new CommentNotExistException();
        commentRepository.deleteById(commentId);
    }
}
