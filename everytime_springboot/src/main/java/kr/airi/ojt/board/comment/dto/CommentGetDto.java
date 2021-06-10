package kr.airi.ojt.board.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentGetDto {

  private Long commentId;
  private Long postId;
  private Long parentCommentId;
  private String content;
  private Long sequence;
}
