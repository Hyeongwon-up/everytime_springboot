package com.example.toy.src.board.repository;

import com.example.toy.src.board.entity.Board;
import com.example.toy.src.board.entity.QBoard;
import com.example.toy.src.post.entity.Post;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.Optional;

public class BoardRepositorySupportImpl
    extends QuerydslRepositorySupport implements BoardRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;
    QBoard board = QBoard.board;

    public BoardRepositorySupportImpl(final JPAQueryFactory jpaQueryFactory) {
        super(Board.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public Optional<Post> findAllBoard() {
        return Optional.empty();
    }

//    @Override
//    public Optional<Post> findPostByBoard() {
//        return Optional.of(jpaQueryFactory
//        .select(post)
//        .)
//    }
}
