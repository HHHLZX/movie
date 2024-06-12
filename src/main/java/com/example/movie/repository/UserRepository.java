package com.example.movie.repository;

import com.example.movie.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByUserPhone(String userPhone);
}