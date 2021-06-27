package com.example.toy.src.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetAllCommentDto {
    private Long idx;
    private String content;
    private Byte isBlind;
    private Long postIdx;
    private Byte status;
    private Long userIdx;
    private Long replyIdx;

//    public PostCommentResDto(NumberPath<Long> cmt_idx, StringPath cmt_content) {
//    }
}
