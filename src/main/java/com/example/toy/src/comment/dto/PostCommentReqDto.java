package com.example.toy.src.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostCommentReqDto {
    private String cmt_content;
    private Long post_idx;
    private Byte is_blind;
}
