package kr.airi.ojt.board.comment.repository;

import java.util.List;
import kr.airi.ojt.board.comment.entity.Comment;

public interface CommentRepositorySupport {

  Long findLastReCommentSeq(Long parentCommentId);

  Long findLastCommentSeq(Long postId);

  Long updateSeqAfterDeleteOne(Long deleteTargetSeq);

  List<Comment> getReCommentListByCommentIdList(List<Long> commentIdList);
}
