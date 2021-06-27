package com.example.toy.src.board.repository;

import com.example.toy.src.board.entity.Board;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    @Override
    List<Board> findAll(Sort sort);
}
