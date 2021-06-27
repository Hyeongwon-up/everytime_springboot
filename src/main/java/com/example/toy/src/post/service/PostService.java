package com.example.toy.src.post.service;

import com.example.toy.src.post.dto.PostPostReqDto;
import com.example.toy.src.post.entity.Post;
import com.example.toy.src.post.repository.PostRepository;
import com.example.toy.src.user.entity.User;
import com.example.toy.src.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class PostService {

  @Autowired
  private PostRepository postRepository;
  @Autowired
  UserRepository userRepository;

  @Transactional
  public String createPost(PostPostReqDto postPostReqDto) {

    User user = userRepository.findById(postPostReqDto.getUser_idx()).get();

    System.out.println("find user");
    Post post = Post.builder()
      .title(postPostReqDto.getTitle())
      .content(postPostReqDto.getContent())
      .user(user)
      .build();

    System.out.println("service finish &*");

    postRepository.save(post);

    return "Success";

  }

  //불러오기만 할 수 있게
  @Transactional(readOnly = true)
  public Post findPost(Long id) {
    Post finded = postRepository.findById(id).get();
    //.orElseThrow(() => new Exception(error));능
    //
    return finded;
  }

  @Transactional(readOnly = true)
  public Post findPostByTitle(String title) {
    Post finded2 = postRepository.findPostByTitle(title).get();
    return finded2;
  }

  @Transactional
  public Post modify(Long id, String title, String content) {
    Post before = postRepository.findById(id).get();

    before.update(title, content);

    return postRepository.save(before);

  }

  @Transactional
  public void delete(Long id) {
    postRepository.deleteById(id);
  }

}
