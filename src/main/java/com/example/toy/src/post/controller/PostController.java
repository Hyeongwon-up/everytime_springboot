package com.example.toy.src.post.controller;

import com.example.toy.src.post.dto.PostPostReqDto;
import com.example.toy.src.post.entity.Post;
import com.example.toy.src.post.repository.PostRepository;
import com.example.toy.src.post.service.PostService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j //로그
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private PostRepository postRepository;

    @PostMapping("/post")
    @ApiOperation(value = "게시글 생성")
    public String create(@RequestBody PostPostReqDto postPostReqDto){
        String tmp = postService.createPost(postPostReqDto);
        return tmp;
    }

    @GetMapping("/get/{post_idx}")
    public Post find(@PathVariable Long post_idx){
        return postService.findPost(post_idx);
    }

    @GetMapping("/get2")
    public Post findByTitle(String title){
//        log.debug(title);
        return postService.findPostByTitle(title);
    }

    @PutMapping("/put/{post_idx}")
    public Post modifyTitle(@PathVariable Long post_idx, String title, String content){
        return postService.modify(post_idx, title, content);
    }

    @DeleteMapping("/delete/{post_idx}")
    public String delete(@PathVariable Long post_idx){
        postService.delete(post_idx);
        return "deleted";
    }

    @GetMapping("/test")
    public Optional<Post> test(Long postId) {
        log.debug("go");
        return postRepository.findById2(postId);
    }
}

