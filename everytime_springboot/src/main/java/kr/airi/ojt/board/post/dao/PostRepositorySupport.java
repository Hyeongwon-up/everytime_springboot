package kr.airi.ojt.board.post.dao;

import kr.airi.ojt.board.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepositorySupport {

  Page<Post> findAll(Pageable pageable);

  Page<Post> findByTitleContaining(String title, Pageable pageable);

  Page<Post> findByContentsContaining(String contents, Pageable pageable);

}
