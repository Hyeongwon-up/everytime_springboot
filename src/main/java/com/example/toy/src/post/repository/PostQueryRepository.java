package com.example.toy.src.post.repository;

import com.example.toy.src.post.entity.Post;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PostQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Autowired
    public PostQueryRepository(JPAQueryFactory jpaQueryFactory){
        this.jpaQueryFactory = jpaQueryFactory;
    }

//    public List<Post> findPostById(long id){
//        return jpaQueryFactory
//                .select()
//                .from()
//                .where()
//                .fetch()
//    }


}
