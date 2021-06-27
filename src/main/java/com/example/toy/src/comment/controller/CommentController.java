package com.example.toy.src.comment.controller;

import com.example.toy.config.BaseResponse;
import com.example.toy.src.comment.dto.PostCommentReqDto;
import com.example.toy.src.comment.entity.Comment;
import com.example.toy.src.comment.repository.CommentRepository;
import com.example.toy.src.comment.repository.CommentRepositorySupport;
import com.example.toy.src.comment.service.CommentService;
import com.example.toy.src.post.entity.Post;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.toy.config.BaseResponseStatus.SUCCESS;

@RestController
@RequestMapping("/comments")
@Slf4j
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private CommentService commentService;

    @PostMapping("")
    @ApiOperation(value = "댓글 작성")
    public BaseResponse<String> postComment(@RequestBody PostCommentReqDto postCommentReqDto){
        String tmp = commentService.createComment(postCommentReqDto);
        return new BaseResponse<>(SUCCESS);
    }

    @GetMapping("/{user_idx}")
    @ApiOperation(value = "유저의 댓글 조회")
    public BaseResponse<List<Comment>> getUserComment(@PathVariable Long user_idx){
        List<Comment> tmp = commentService.findCommentByUser_idx(user_idx);
        return new BaseResponse<>(SUCCESS, tmp);
    }



}
