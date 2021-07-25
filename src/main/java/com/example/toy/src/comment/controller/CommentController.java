package com.example.toy.src.comment.controller;

import com.example.toy.config.BaseException;
import com.example.toy.config.BaseResponse;
import com.example.toy.src.comment.dto.GetAllCommentDto;
import com.example.toy.src.comment.dto.GetCommentResDto;
import com.example.toy.src.comment.dto.PostCommentDto;
import com.example.toy.src.comment.service.CommentService;
import com.example.toy.utils.JwtService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.toy.config.BaseResponseStatus.*;

@RestController
@Slf4j
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired  // 아 이거 안 하면 X-ACCESS-TOKEN 인식을 못한다....
    private JwtService jwtService;

    @PostMapping("/comments")
    @ApiOperation(value = "댓글/대댓글 작성")
    public BaseResponse<String> postComment(@RequestBody PostCommentDto postCommentDto) throws BaseException {
//        long tokenUserId;

//        try{
//            tokenUserId = jwtService.getUser_idx();
//        }catch(Exception e){
//            return new BaseResponse<>(INVALID_TOKEN);
//        }
        try {
            commentService.createComment(postCommentDto);
        }catch(BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
        return new BaseResponse<>(SUCCESS_POST_COMMENT);
    }

//    @GetMapping("/comments/mine")
//    @ApiOperation(value = "유저의 댓글 조회")
//    public BaseResponse<List<GetCommentResDto>> getUserComment(@RequestHeader("X-ACCESS-TOKEN") String token) throws BaseException {
//
//        long tokenUserId;
//        try{
//            tokenUserId = jwtService.getUser_idx();
//        }catch(Exception e){
//            return new BaseResponse<>(INVALID_TOKEN);
//        }
//        List<GetCommentResDto> tmp = commentService.findCommentByUserIdx(tokenUserId);
//        return new BaseResponse<>(SUCCESS, tmp);
//    }

    @GetMapping("/comments/post/{post_idx}")
    @ApiOperation(value = "게시글의 댓글 조회")
    public List<GetAllCommentDto> getComments(@PathVariable Long post_idx) {
        List<GetAllCommentDto> tmp = commentService.getAllCommentsByPostIdx(post_idx);
        return tmp;
    }


}
