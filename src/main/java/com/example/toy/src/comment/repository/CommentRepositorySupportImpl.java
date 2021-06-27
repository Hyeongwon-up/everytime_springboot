package com.example.toy.src.comment.repository;

import com.example.toy.src.comment.dto.GetCommentResDto;
import com.example.toy.src.comment.entity.Comment;
import com.example.toy.src.comment.entity.QComment;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class CommentRepositorySupportImpl
    extends QuerydslRepositorySupport implements CommentRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;
    QComment comment = QComment.comment;

    public CommentRepositorySupportImpl(final JPAQueryFactory jpaQueryFactory) {
        super(Comment.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }
//
//    @Override
    public List<GetCommentResDto> findCommentsByUser_idx(Long user_idx) {
        return jpaQueryFactory
                .select((Projections.constructor(GetCommentResDto.class,
                        comment.cmt_idx, comment.cmt_content, comment.is_blind
                , comment.post.post_idx, comment.status, comment.user_idx )))
                .from(comment)
                .where(comment.user_idx.eq(user_idx))
                .fetch();
    }
}
