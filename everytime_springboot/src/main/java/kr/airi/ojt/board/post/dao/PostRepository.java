package kr.airi.ojt.board.post.dao;

import kr.airi.ojt.board.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>, PostRepositorySupport {

}
