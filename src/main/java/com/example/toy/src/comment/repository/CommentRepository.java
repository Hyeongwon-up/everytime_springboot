package com.example.toy.src.comment.repository;

import com.example.toy.src.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>
        ,CommentRepositorySupport{

    boolean existsCommentByIdx(Long idx);
    Optional<Comment> findCommentByIdx(Long idx);
}
