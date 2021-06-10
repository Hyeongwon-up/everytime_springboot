package kr.airi.ojt.board.post.dto;

import java.util.List;
import kr.airi.ojt.board.comment.entity.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class PostPutDto {

  @Getter
  @Setter
  @NoArgsConstructor
  public static class PostPutReqDto {

    private Long id;
    private String title;
    private String contents;

    @Builder
    public PostPutReqDto(Long id, String title, String contents) {
      this.id = id;
      this.title = title;
      this.contents = contents;
    }
  }

  @Getter
  @Setter
  @NoArgsConstructor
  public static class PostPutResDto {

    private Long id;
    private String title;
    private String contents;
    private Long views;
    private List<Comment> comment;

    @Builder
    public PostPutResDto(Long id, String title, String contents, Long views,
        List<Comment> comment) {
      this.id = id;
      this.title = title;
      this.contents = contents;
      this.views = views;
      this.comment = comment;
    }

  }

}
