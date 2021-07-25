package com.example.toy.src.comment.dto;

import lombok.*;

@Getter
@NoArgsConstructor
public class PostCommentDto {

        private String content;
        private Long userIdx;
        private Long postIdx;
        private Long parentCmtIdx;
        private Byte isBlind;
        // status

}
