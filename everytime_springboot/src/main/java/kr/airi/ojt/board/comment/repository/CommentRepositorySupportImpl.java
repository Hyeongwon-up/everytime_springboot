package kr.airi.ojt.board.comment.repository;


import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import kr.airi.ojt.board.comment.entity.Comment;
import kr.airi.ojt.board.comment.entity.QComment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

@Slf4j
public class CommentRepositorySupportImpl extends QuerydslRepositorySupport implements
    CommentRepositorySupport {

  private final JPAQueryFactory queryFactory;
  QComment comment = QComment.comment;

  public CommentRepositorySupportImpl(final JPAQueryFactory queryFactory) {
    super(Comment.class);
    this.queryFactory = queryFactory;
  }

  @Override
  public Long findLastReCommentSeq(Long parentCommentId) {
    return queryFactory.select(comment.sequence).from(comment)
        .where(comment.parentCommentId.eq(parentCommentId))
        .orderBy(comment.sequence.desc()).fetchFirst();

  }

  @Override
  public Long findLastCommentSeq(Long postId) {
    return queryFactory.select(comment.sequence).from(comment).where(comment.post().id.eq(postId))
        .orderBy(comment.sequence.desc()).fetchFirst();
  }

  @Override
  public Long updateSeqAfterDeleteOne(Long deleteTargetSeq) {
    return queryFactory.update(comment)
        .where(comment.sequence.gt(deleteTargetSeq))
        .set(comment.sequence, comment.sequence.subtract(1))
        .execute();
  }

  @Override
  public List<Comment> getReCommentListByCommentIdList(List<Long> commentIdList) {
    return queryFactory.selectFrom(comment).where(comment.parentCommentId.isNotNull())
        .where(comment.parentCommentId.in(commentIdList)).fetch();
  }
}
