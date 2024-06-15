package com.example.movie.repository;

import com.example.movie.entity.MessageBoards;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MessageBoardsRepository extends JpaRepository<MessageBoards, Integer> {
    public Optional findById(int id);
}
