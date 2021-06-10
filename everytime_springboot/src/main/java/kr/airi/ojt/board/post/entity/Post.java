package kr.airi.ojt.board.post.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import kr.airi.ojt.board.comment.entity.Comment;
import kr.airi.ojt.board.global.ModelMapperUtils;
import kr.airi.ojt.board.global.entity.BaseTimeEntity;
import kr.airi.ojt.board.post.dto.PostGetDto;
import kr.airi.ojt.board.post.dto.PostPutDto.PostPutReqDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "post")
public class Post extends BaseTimeEntity {

  @Id
  @Column(name = "post_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  private String title;
  private String contents;

  @Setter
  @ColumnDefault("0")
  private Long views;

  @Setter
  @JsonManagedReference
  @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Comment> comment = new HashSet<>();

  @Builder
  public Post(String title, String contents, Long views,
      Set<Comment> comment) {
    this.title = title;
    this.contents = contents;
    this.views = views;
    this.comment = comment;
  }

  public void update(PostPutReqDto postPutReqDto) {
    if (postPutReqDto.getTitle() != null && postPutReqDto.getContents() != null) {
      this.title = postPutReqDto.getTitle();
      this.contents = postPutReqDto.getContents();
    }
  }

  public void addViews(Long views) {
    if (views != null) {
      this.views = views;
    }
  }

  public PostGetDto toDto() {
    return ModelMapperUtils.getInstance().map(this, PostGetDto.class);
  }

}
