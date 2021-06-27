package com.example.toy.src.comment.dto;

import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

public class PostCommentResDto {
    private Long cmt_idx;
    private String cmt_content;
    private Byte is_blind;
    private Long post_idx;
    private Byte status;
    private Long user_idx;

//    public PostCommentResDto(NumberPath<Long> cmt_idx, StringPath cmt_content) {
//    }
}
