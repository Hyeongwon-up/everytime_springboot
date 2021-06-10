package kr.airi.ojt.board.comment.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import kr.airi.ojt.board.global.code.YnCode;
import kr.airi.ojt.board.global.entity.BaseTimeEntity;
import kr.airi.ojt.board.post.entity.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Entity
@Getter
@NoArgsConstructor
@Table(name = "comment")
public class Comment extends BaseTimeEntity {

  @Id
  @Column(name = "comment_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Setter
  @Column(nullable = false)
  private String content;

  @JsonBackReference
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "post_id")
  private Post post;

  @Column(name = "parent_comment_id")
  private Long parentCommentId;

  @Setter
  @Column(nullable = false, columnDefinition = "bigint default 0")
  private Long sequence;

  @Setter
  @Enumerated(EnumType.STRING)
  @Column(nullable = false, columnDefinition = "char default 'N'")
  private YnCode isRemoved = YnCode.N;

  @Builder
  public Comment(String content, Post post, Long parentCommentId, Long sequence) {
    this.content = content;
    this.post = post;
    this.parentCommentId = parentCommentId;
    this.sequence = sequence;
  }
}
