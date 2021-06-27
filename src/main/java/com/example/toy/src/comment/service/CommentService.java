package com.example.toy.src.comment.service;

import com.example.toy.config.BaseException;
import com.example.toy.src.comment.dto.GetAllCommentDto;
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

import static com.example.toy.config.BaseResponseStatus.*;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    PostRepository postRepository;

    @Transactional
    public String createComment(Long userIdx, PostCommentReqDto postCommentReqDto) throws BaseException {

        Post post;
        long replyIdx;

        if(postCommentReqDto.getPostIdx() != null && postCommentReqDto.getPostIdx() > 0){
            // 댓글일 경우
            post = postRepository.findById(postCommentReqDto.getPostIdx()).get();
            replyIdx = 0;
        } else if(postCommentReqDto.getReplyIdx() != null && postCommentReqDto.getReplyIdx() > 0) {
            // 대댓글일 경우
            if (!commentRepository.existsById(postCommentReqDto.getReplyIdx())) {
                throw new BaseException(NOT_EXIST_COMMENT);
            }
            Comment findedComment = commentRepository.findCommentByIdx(postCommentReqDto.getReplyIdx()).get();
            post = findedComment.getPost();
            replyIdx = postCommentReqDto.getReplyIdx();
        }else {
            throw new BaseException(NOT_ENTERED_COMMENT);
        }

        Comment comment = Comment.builder()
                .content(postCommentReqDto.getContent())
                .userIdx(userIdx)
                .post(post)
                .replyIdx(replyIdx)
                .isBlind(postCommentReqDto.getIsBlind())
                .build();

        commentRepository.save(comment);

        return "Success";
    }

    public List<GetCommentResDto> findCommentByUserIdx(Long userIdx){
        List<GetCommentResDto> commentList = commentRepository.findCommentByUserIdx(userIdx);
        return commentList;
    }

    public List<GetAllCommentDto> getAllCommentsByPostIdx(Long postIdx){
        List<GetAllCommentDto> commentList = commentRepository.findAllByPostIdx(postIdx);
        return commentList;
    }

}
