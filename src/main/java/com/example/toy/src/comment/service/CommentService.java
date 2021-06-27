package com.example.toy.src.comment.service;

import com.example.toy.src.comment.dto.GetCommentResDto;
import com.example.toy.src.comment.dto.PostCommentReqDto;
import com.example.toy.src.comment.entity.Comment;
import com.example.toy.src.comment.repository.CommentRepository;
import com.example.toy.src.post.entity.Post;
import com.example.toy.src.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    PostRepository postRepository;

    @Transactional
    public String createComment(Long userIdx, PostCommentReqDto postCommentReqDto){

        Post post = postRepository.findById(postCommentReqDto.getPost_idx()).get();

        Comment comment = Comment.builder()
                .cmt_content(postCommentReqDto.getCmt_content())
                .user_idx(userIdx)
                .post(post)
                .is_blind(postCommentReqDto.getIs_blind())
                .build();

        commentRepository.save(comment);

        return "Success";
    }

    public List<GetCommentResDto> findCommentByUser_idx(Long user_idx){
        List<GetCommentResDto> commentList = commentRepository.findCommentsByUser_idx(user_idx);
        return commentList;
    }

}
