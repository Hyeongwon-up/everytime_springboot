package com.example.toy.src.comment.controller;

import com.example.toy.config.BaseException;
import com.example.toy.config.BaseResponse;
import com.example.toy.config.BaseResponseStatus;
import com.example.toy.src.comment.dto.GetCommentResDto;
import com.example.toy.src.comment.dto.PostCommentReqDto;
import com.example.toy.src.comment.entity.Comment;
import com.example.toy.src.comment.repository.CommentRepository;
import com.example.toy.src.comment.repository.CommentRepositorySupport;
import com.example.toy.src.comment.service.CommentService;
import com.example.toy.src.post.entity.Post;
import com.example.toy.utils.JwtService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.toy.config.BaseResponseStatus.INVALID_TOKEN;
import static com.example.toy.config.BaseResponseStatus.SUCCESS;

@RestController
@RequestMapping("/comments")
@Slf4j
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private CommentService commentService;
    @Autowired  // 아 이거 안 하면 X-ACCESS-TOKEN 인식을 못한다....
    private JwtService jwtService;

    @PostMapping("")
    @ApiOperation(value = "댓글 작성")
    public BaseResponse<String> postComment(@RequestHeader("X-ACCESS-TOKEN") String token,
                                            @RequestBody PostCommentReqDto postCommentReqDto) throws BaseException {

        long tokenUserId;

        try{
            log.info("토큰의 유저인덱스를 한번 찾아봅시다");
            tokenUserId = jwtService.getUser_idx();
        }catch(Exception e){
            return new BaseResponse<>(INVALID_TOKEN);
        }
        String tmp = commentService.createComment(tokenUserId, postCommentReqDto);
        return new BaseResponse<>(SUCCESS);
    }

    @GetMapping("/mine")
    @ApiOperation(value = "유저의 댓글 조회")
    public BaseResponse<List<GetCommentResDto>> getUserComment(@RequestHeader("X-ACCESS-TOKEN") String token) throws BaseException {

        long tokenUserId;
        tokenUserId = jwtService.getUser_idx();

        List<GetCommentResDto> tmp = commentService.findCommentByUser_idx(tokenUserId);
        return new BaseResponse<>(SUCCESS, tmp);
    }



}
