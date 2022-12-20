package com.sparta.spartapost.repository;

import com.sparta.spartapost.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllOrderByModifiedAtDesc();
    List<Comment> findByPostIdOrderByModifiedAtDesc();
}
