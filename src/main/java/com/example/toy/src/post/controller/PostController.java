package com.example.toy.src.post.controller;

import com.example.toy.src.post.dto.PostPostReqDto;
import com.example.toy.src.post.entity.Post;
import com.example.toy.src.post.repository.PostRepository;
import com.example.toy.src.post.service.PostService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j //로그
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    PostRepository postRepository;
    private PostPostReqDto postPostReqDto;

    @PostMapping("/post")
    @ApiOperation(value = "게시글 생성")
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

    @GetMapping("/test")
    public Optional<Post> test(Long postId) {
        log.debug("go");
        return postRepository.findById2(postId);
    }
}

