package com.example.toy.src.comment.dto;

import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetCommentResDto {
    private Long idx;
    private String content;
    private Byte isBlind;
    private Long postIdx;
    private Byte status;
    private Long userIdx;

//    public PostCommentResDto(NumberPath<Long> cmt_idx, StringPath cmt_content) {
//    }
}
