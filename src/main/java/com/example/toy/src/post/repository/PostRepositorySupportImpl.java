package com.example.toy.src.post.repository;

import com.example.toy.src.post.entity.Post;
import com.example.toy.src.post.entity.QPost;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.Optional;


public class PostRepositorySupportImpl extends QuerydslRepositorySupport implements PostRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;
    QPost post = QPost.post;

    public PostRepositorySupportImpl(final JPAQueryFactory jpaQueryFactory) {
        super(Post.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

//    @Override
//    public List<Post> findById2(Long postId) {
    @Override
    public Optional<Post> findById2(Long postId) {
        return Optional.of(jpaQueryFactory
                .select(post)
                .from(post)
                .where(post.id.eq(postId))
                .fetchOne()
        );
    }
}
