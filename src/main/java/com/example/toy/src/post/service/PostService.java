package com.example.toy.src.post.service;

import com.example.toy.src.post.dto.PostPostReqDto;
import com.example.toy.src.post.entity.Post;
import com.example.toy.src.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Transactional
    public  String createPost(PostPostReqDto postPostReqDto){

        Post post = Post.builder()
                .title(postPostReqDto.getTitle())
                .content(postPostReqDto.getContent())
                .board_idx(postPostReqDto.getBoard_idx())
                .user_idx(postPostReqDto.getUser_idx())
                .is_blind(postPostReqDto.getIs_blind())
                .status(postPostReqDto.getStatus())
                .build();

        postRepository.save(post);

        return "Success";

    }

    //불러오기만 할 수 있게
    @Transactional(readOnly = true)
    public Post findPost(Long id){
        Post finded = postRepository.findById(id).get();
        //.orElseThrow(() => new Exception(error));능
        //
        return finded;
    }

    @Transactional(readOnly = true)
    public Post findPostByTitle(String title){
        Post finded2 = postRepository.findPostByTitle(title).get();
        return finded2;
    }

    @Transactional
    public Post modify(Long id, String title, String content){
        Post before = postRepository.findById(id).get();

        before.update(title, content);

        return postRepository.save(before);

    }

    @Transactional
    public void delete(Long id){
        postRepository.deleteById(id);
    }

}
