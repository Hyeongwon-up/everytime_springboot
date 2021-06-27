package com.example.toy.src.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostCommentReqDto {
    private String content;
    private Long postIdx;
    private Long replyIdx;
    private Byte isBlind;
}
