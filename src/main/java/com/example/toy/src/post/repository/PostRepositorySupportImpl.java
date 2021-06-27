package com.example.toy.src.post.repository;

import com.example.toy.src.post.entity.Post;
import com.example.toy.src.post.entity.QPost;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.Optional;

@Slf4j
public class PostRepositorySupportImpl
  extends QuerydslRepositorySupport implements PostRepositorySupport {

  private final JPAQueryFactory jpaQueryFactory;
  QPost post = QPost.post;

  public PostRepositorySupportImpl(final JPAQueryFactory jpaQueryFactory) {
    super(Post.class);
    this.jpaQueryFactory = jpaQueryFactory;
  }

  @Override
  public List<Post> findPostOrderBydesc() {
    log.debug("finish");

    return jpaQueryFactory.selectFrom(post)
      .orderBy(post.post_idx.desc())
      .fetch();
  }

  @Override
  public Optional<Post> findById2(Long postId) {
    return Optional.of(jpaQueryFactory
      .select(post)
      .from(post)
      .where(post.post_idx.eq(postId))
      .fetchOne()
    );
  }


}
