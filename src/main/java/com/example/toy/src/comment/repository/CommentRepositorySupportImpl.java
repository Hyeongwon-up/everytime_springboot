package com.example.toy.src.comment.repository;

import com.example.toy.src.comment.dto.GetAllCommentDto;
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
    public List<GetCommentResDto> findCommentByUserIdx(Long userIdx) {
        return jpaQueryFactory
                .select((Projections.constructor(GetCommentResDto.class,
                        comment.idx, comment.content, comment.isBlind
                , comment.post.post_idx, comment.status, comment.userIdx )))
                .from(comment)
                .where(comment.userIdx.eq(userIdx))
                .fetch();
    }

    @Override
    public List<GetAllCommentDto> findAllByPostIdx(Long postIdx) {
        return jpaQueryFactory
                .select((Projections.constructor(GetAllCommentDto.class,
                        comment.idx, comment.content, comment.isBlind
                        , comment.post.post_idx, comment.status, comment.userIdx, comment.replyIdx )))
                .from(comment)
                .where(comment.post.post_idx.eq(postIdx))
                .fetch();
    }
}
