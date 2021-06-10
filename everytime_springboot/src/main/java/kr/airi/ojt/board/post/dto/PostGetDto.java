package kr.airi.ojt.board.post.dto;

import java.time.LocalDateTime;
import java.util.List;
import kr.airi.ojt.board.comment.entity.Comment;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostGetDto {

  private Long id;
  private String title;
  private String contents;
  private Long views;
  private List<Comment> comment;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  @Builder
  public PostGetDto(Long id, String title, String contents, Long views,
      List<Comment> comment, LocalDateTime createdAt, LocalDateTime updatedAt) {
    this.id = id;
    this.title = title;
    this.contents = contents;
    this.views = views;
    this.comment = comment;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

}
