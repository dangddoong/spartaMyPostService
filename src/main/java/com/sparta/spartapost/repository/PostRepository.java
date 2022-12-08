package com.sparta.spartapost.repository;

import com.sparta.spartapost.dto.PostResponseDto;
import com.sparta.spartapost.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByModifiedAtDesc();
}
