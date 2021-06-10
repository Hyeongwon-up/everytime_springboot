package kr.airi.ojt.board.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class CommentPutDto {

  @ToString
  @Getter
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class CommentPutReqDto {

    private String content;
  }

  @ToString
  @Getter
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class CommentPutResDto {

    private Long commentId;
    private Long postId;
    private Long parentCommentId;
    private String content;
    private Long sequence;
  }

}
