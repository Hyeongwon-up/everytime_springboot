package kr.airi.ojt.board.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


public class CommentPostDto {

  @ToString
  @Getter
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class CommentPostReqDto {

    private Long postId;
    private Long parentCommentId;
    private String content;
  }

  @ToString
  @Getter
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class CommentPostResDto {

    private Long commentId;
    private Long postId;
    private Long parentCommentId;
    private String content;
    private Long sequence;
  }


}
