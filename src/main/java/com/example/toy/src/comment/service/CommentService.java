package com.example.toy.src.comment.service;

import com.example.toy.config.BaseException;
import com.example.toy.src.comment.dto.GetAllCommentDto;
import com.example.toy.src.comment.dto.GetCommentResDto;
import com.example.toy.src.comment.dto.PostCommentDto;
import com.example.toy.src.comment.entity.Comment;
import com.example.toy.src.comment.repository.CommentRepository;
import com.example.toy.src.post.entity.Post;
import com.example.toy.src.post.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.toy.config.BaseResponseStatus.*;

@Service
@Slf4j
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    PostRepository postRepository;

    @Transactional
    public String createComment(PostCommentDto reqDto) throws BaseException {

        Post post;
        Long parentCmtIdx;
        Long seq;

        // postIdx가 있는지에 대한 유효성 검사 X
        if(reqDto.getPostIdx() != null && reqDto.getPostIdx() > 0){
            // 댓글일 경우(postIdx가 들어오기만 하면 댓글로 판단)
            post = postRepository.findById(reqDto.getPostIdx()).get();
            parentCmtIdx = 0L;
            seq = 1L;

        } else if(reqDto.getParentCmtIdx() != null && reqDto.getParentCmtIdx() > 0) {
            // 대댓글일 경우(그게 아니라면 대댓글로 판단)
//            if (!commentRepository.existsById(reqDto.getParentCmtIdx())) {
//                throw new BaseException(NOT_EXIST_COMMENT);
//            }
//            post = commentRepository.findCommentB(reqDto.getPostIdx()).get();
            parentCmtIdx = reqDto.getParentCmtIdx();
            post = commentRepository.findCommentByIdx(parentCmtIdx).get().getPost();
            seq = commentRepository.findLatestSeq(parentCmtIdx);
        } else {
            throw new BaseException(NOT_ENTERED_COMMENT);
        }


        Comment comment = Comment
                .builder()
                .content(reqDto.getContent())
                .userIdx(reqDto.getUserIdx())
                .post(post)
                .parentCmtIdx(parentCmtIdx)
                .seq(seq)
                .isBlind(reqDto.getIsBlind())
                .status((byte) 1)
                .build();

        commentRepository.save(comment);

        return "Success";
    }

    public List<GetCommentResDto> findCommentByUserIdx(Long userIdx){
        List<GetCommentResDto> commentList = commentRepository.findCommentByUserIdx(userIdx);
        return commentList;
    }

    public List<GetAllCommentDto> getAllCommentsByPostIdx(Long post_idx){
        List<GetAllCommentDto> commentList = commentRepository.findAllByPostIdx(post_idx);
        log.info(String.valueOf(commentList));
        return commentList;
    }

}
