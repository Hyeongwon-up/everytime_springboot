package com.example.toy.src.comment.repository;

import com.example.toy.src.comment.dto.PostCommentResDto;
import com.example.toy.src.comment.entity.Comment;
import com.example.toy.src.comment.entity.QComment;
import com.example.toy.src.post.entity.QPost;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import com.querydsl.core.types.Projections;

import java.util.List;
import java.util.Optional;

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
    public List<Comment> findCommentsByUser_idx(Long user_idx) {
        return jpaQueryFactory
                .select(comment)
                .from(comment)
                .where(comment.user_idx.eq(user_idx))
                .fetch();
    }
}
