package kr.airi.ojt.board.comment.service;

import javax.persistence.EntityNotFoundException;
import kr.airi.ojt.board.comment.dto.CommentPostDto.CommentPostReqDto;
import kr.airi.ojt.board.comment.dto.CommentPostDto.CommentPostResDto;
import kr.airi.ojt.board.comment.dto.CommentPutDto.CommentPutReqDto;
import kr.airi.ojt.board.comment.dto.CommentPutDto.CommentPutResDto;
import kr.airi.ojt.board.comment.entity.Comment;
import kr.airi.ojt.board.comment.repository.CommentRepository;
import kr.airi.ojt.board.global.code.YnCode;
import kr.airi.ojt.board.post.dao.PostRepository;
import kr.airi.ojt.board.post.entity.Post;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {

  private final CommentRepository commentRepository;
  private final PostRepository postRepository;

  @Transactional
  public CommentPostResDto post(CommentPostReqDto reqDto) {
    Long lastSeq = 0L;
    Comment newEntity;

    log.debug("reqDto: {} {}", reqDto.getPostId(), reqDto.getParentCommentId());

    if (reqDto.getPostId() != null && reqDto.getPostId() > 0) {
      lastSeq = commentRepository.findLastCommentSeq(reqDto.getPostId());

      Post foundPost = postRepository.findById(reqDto.getPostId())
          .orElseThrow(() -> new EntityNotFoundException("wrong post id" + reqDto.getPostId()));

      newEntity = Comment.builder().post(foundPost).content(reqDto.getContent()).build();
      log.debug("found lastSeq: {} - post id: {}", lastSeq, reqDto.getPostId());
    } else if (reqDto.getParentCommentId() != null && reqDto.getParentCommentId() > 0) {
      lastSeq = commentRepository.findLastReCommentSeq(reqDto.getParentCommentId());

      newEntity = Comment.builder().parentCommentId(reqDto.getParentCommentId())
          .content(reqDto.getContent()).build();

      log.debug("found lastSeq: {} - parent comment id: {}", lastSeq, reqDto.getParentCommentId());
    } else {
      throw new IllegalArgumentException("post id or parent comment id must be entered!");
    }

    if (lastSeq != null && lastSeq >= 0) {
      newEntity.setSequence(lastSeq + 1);
    } else {
      newEntity.setSequence(0L);
    }

    commentRepository.save(newEntity);
    log.debug("new entity: {}", newEntity.toString());

    return CommentPostResDto.builder().commentId(newEntity.getId())
        .postId(getPostIdFromEntity(newEntity))
        .content(newEntity.getContent())
        .parentCommentId(newEntity.getParentCommentId())
        .sequence(newEntity.getSequence())
        .build();
  }

  @Transactional
  public CommentPutResDto put(Long commentId, CommentPutReqDto reqDto) {
    Comment targetEntity = commentRepository.findById(commentId)
        .orElseThrow(() -> new EntityNotFoundException("wrong comment id - " + commentId));

    targetEntity.setContent(reqDto.getContent());

    commentRepository.save(targetEntity);

    return CommentPutResDto.builder().commentId(targetEntity.getId())
        .parentCommentId(targetEntity.getParentCommentId())
        .content(targetEntity.getContent())
        .postId(getPostIdFromEntity(targetEntity))
        .sequence(targetEntity.getSequence())
        .build();
  }

  @Transactional
  public Long delete(Long commentId) {
    Comment targetEntity = commentRepository.findById(commentId)
        .orElseThrow(() -> new EntityNotFoundException("wrong comment id - " + commentId));

    targetEntity.setIsRemoved(YnCode.Y);
    commentRepository.save(targetEntity);
    return commentId;
  }

  private Long getPostIdFromEntity(Comment entity) {
    Long postId = null;
    if (entity.getPost() != null) {
      postId = entity.getPost().getId();
    }
    return postId;
  }
}
