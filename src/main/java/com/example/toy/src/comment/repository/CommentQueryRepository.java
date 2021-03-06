package com.example.toy.src.comment.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class CommentQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Autowired
    public CommentQueryRepository(JPAQueryFactory jpaQueryFactory){
        this.jpaQueryFactory = jpaQueryFactory;
    }

}
