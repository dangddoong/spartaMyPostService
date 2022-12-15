package com.sparta.spartapost.repository;

import com.sparta.spartapost.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
