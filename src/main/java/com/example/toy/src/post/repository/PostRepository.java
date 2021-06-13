package com.example.toy.src.post.repository;

import com.example.toy.src.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findPostByTitle(String title);
    //Optional -> 에러 메시지 처리 가능

//    Post findByContent


}
