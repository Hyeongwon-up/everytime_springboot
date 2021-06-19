package com.example.toy.src.post.repository;

import com.example.toy.src.post.entity.Post;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepositorySupport {
    Optional<Post> findById2(Long postId);
}
