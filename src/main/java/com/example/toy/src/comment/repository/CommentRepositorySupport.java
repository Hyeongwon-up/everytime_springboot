package com.example.toy.src.comment.repository;

import com.example.toy.src.comment.entity.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepositorySupport {

    List<Comment> findCommentsByUser_idx(Long user_idx);

}
