package com.example.toy.src.post.controller;

import com.example.toy.src.post.dto.PostPostReqDto;
import com.example.toy.src.post.entity.Post;
import com.example.toy.src.post.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j //로그
public class PostController {

    @Autowired
    private PostService postService;
    private PostPostReqDto postPostReqDto;

    @PostMapping("/post")
    public String create(@RequestBody PostPostReqDto postPostReqDto){
        String tmp = postService.createPost(postPostReqDto);
        return tmp;
    }

    @GetMapping("/get/{id}")
    public Post find(@PathVariable Long id){
        return postService.findPost(id);
    }

    @GetMapping("/get2")
    public Post findByTitle(String title){
//        log.debug(title);
        return postService.findPostByTitle(title);
    }

    @PutMapping("/put/{id}")
    public Post modifyTitle(@PathVariable Long id, String title, String content){
        return postService.modify(id, title, content);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        postService.delete(id);
        return "deleted";
    }
}

