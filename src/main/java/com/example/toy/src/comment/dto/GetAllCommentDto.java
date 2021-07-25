package com.example.toy.src.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class GetAllCommentDto {
    private Long idx;
    private String content;
    private Long userIdx;
    private Long postIdx;
    private Long parentCmtIdx;
    private Long seq;
    private Byte isBlind;
    private Byte status;

//    public GetAllCommentDto(long idx, String content, long userIdx, long postIdx,
//                            long parentCmtIdx, long seq, byte isBlind, byte status,
//                            Timestamp createdAt, Timestamp updatedAt) {
////        this.idx = idx;
//        this.content = content;
//        this.userIdx = userIdx;
//        this.postIdx = postIdx;
//        this.parentCmtIdx = parentCmtIdx;
//        this.seq = seq;
//        this.isBlind = isBlind;
//        this.status = status;
//        this.createdAt = createdAt;
//        this.updatedAt = updatedAt;
//    }
//    public PostCommentResDto(NumberPath<Long> cmt_idx, StringPath cmt_content) {
//    }
}
