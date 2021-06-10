package kr.airi.ojt.board.post.dto;

import java.util.List;
import kr.airi.ojt.board.comment.entity.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class PostPostDto {

  @Getter
  @Setter
  @NoArgsConstructor
  public static class PostPostReqDto {

    private String title;
    private String contents;
    private Long views;

    @Builder
    public PostPostReqDto(String title, String contents, Long views) {
      this.title = title;
      this.contents = contents;
      this.views = views;
    }
  }

  @Getter
  @Setter
  @NoArgsConstructor
  public static class PostPostResDto {

    private Long id;
    private String title;
    private String contents;
    private Long views;
    private List<Comment> comment;

    @Builder
    public PostPostResDto(Long id, String title, String contents, Long views,
        List<Comment> comment) {
      this.id = id;
      this.title = title;
      this.contents = contents;
      this.views = views;
      this.comment = comment;
    }

  }

}
