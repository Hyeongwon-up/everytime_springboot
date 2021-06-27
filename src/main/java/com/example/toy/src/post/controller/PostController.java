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
  public String create(@RequestBody PostPostReqDto postPostReqDto) {
    String tmp = postService.createPost(postPostReqDto);
    return tmp;
  }

  @GetMapping("/get/{post_id}")
  @ApiOperation(value = "게시글 조회")
  public Post find(@PathVariable Long post_id) {
    return postService.findPost(post_id);
  }

  @GetMapping("/get2")
  @ApiOperation(value = "해당 제목의 게시글 조회")
  public Post findByTitle(String title) {
    return postService.findPostByTitle(title);
  }

  @PutMapping("/put/{post_id}")
  @ApiOperation(value = "게시글 수정")
  public Post modifyTitle(@PathVariable Long post_id, String title, String content) {
    return postService.modify(post_id, title, content);
  }

  @DeleteMapping("/delete/{id}")
  @ApiOperation(value = "게시글 삭제")
  public String delete(@PathVariable Long id) {
    postService.delete(id);
    return "deleted";
  }

  @GetMapping("/test")
  @ApiOperation(value = "게시글 수정")
  public Optional<Post> test(Long postId) {
    return postRepository.findById2(postId);
  }
}

