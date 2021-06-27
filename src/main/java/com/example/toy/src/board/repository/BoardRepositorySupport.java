package com.example.toy.src.board.repository;

import com.example.toy.src.board.entity.Board;
import com.example.toy.src.post.entity.Post;
import jdk.nashorn.internal.ir.Optimistic;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepositorySupport {

    Optional<Post> findAllBoard();
}
