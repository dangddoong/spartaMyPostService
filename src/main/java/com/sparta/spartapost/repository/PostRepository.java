package com.sparta.spartapost.repository;

import com.sparta.spartapost.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
