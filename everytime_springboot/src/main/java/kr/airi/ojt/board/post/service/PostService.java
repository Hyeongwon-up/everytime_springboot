package kr.airi.ojt.board.post.service;

import static kr.airi.ojt.board.post.dto.PostPostDto.PostPostReqDto;
import static kr.airi.ojt.board.post.dto.PostPostDto.PostPostResDto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import kr.airi.ojt.board.comment.entity.Comment;
import kr.airi.ojt.board.comment.repository.CommentRepository;
import kr.airi.ojt.board.post.dao.PostRepository;
import kr.airi.ojt.board.post.dto.PostGetDto;
import kr.airi.ojt.board.post.dto.PostPutDto.PostPutReqDto;
import kr.airi.ojt.board.post.dto.PostPutDto.PostPutResDto;
import kr.airi.ojt.board.post.entity.Post;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {

  private final PostRepository postRepository;
  private final CommentRepository commentRepository;
  private final ModelMapper modelMapper;

  @Transactional(readOnly = true)
  public Page<PostGetDto> getAll(Pageable paging, String title, String contents) {
    Page<Post> postList;
    if (title != null) {
      postList = postRepository.findByTitleContaining(title, paging);
    } else if (contents != null) {
      postList = postRepository.findByContentsContaining(contents, paging);
    } else {
      postList = postRepository.findAll(paging);
    }

    postList.forEach((post) -> {
          Set<Comment> commentSet = new HashSet<>(post.getComment());
          List<Long> commentIdList = post.getComment().stream().map(Comment::getId)
              .collect(Collectors.toList());

          List<Comment> reCommentList = commentRepository
              .getReCommentListByCommentIdList(commentIdList);

          commentSet.addAll(reCommentList);
          post.setComment(commentSet);
        }
    );

    if (postList.getContent().size() != 0) {
      return postList.map(post -> modelMapper.map(post, PostGetDto.class));
    }

    return null;
  }

  @Transactional
  public PostGetDto get(Long id) {
    Post post = postRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(id.toString()));
    PostGetDto postGetDto = post.toDto();

    Set<Comment> commentSet = new HashSet<>(postGetDto.getComment());
    List<Long> commentIdList = postGetDto.getComment().stream().map(Comment::getId)
        .collect(Collectors.toList());
    List<Comment> reCommentList = commentRepository
        .getReCommentListByCommentIdList(commentIdList);

    commentSet.addAll(reCommentList);
    List<Comment> commentList = new ArrayList<>(commentSet);
    postGetDto.setComment(commentList);
    postGetDto.setViews(post.getViews() + 1);

    post.setViews(postGetDto.getViews());
    postRepository.save(post);
    return postGetDto;
  }

  @Transactional
  public PostPostResDto create(PostPostReqDto postPostReqDto) {
    Post post = Post.builder()
        .title(postPostReqDto.getTitle())
        .contents(postPostReqDto.getContents())
        .views(0L)
        .build();
    Post savedPost = postRepository.save(post);
    return modelMapper.map(savedPost, PostPostResDto.class);
  }

  @Transactional
  public PostPutResDto update(PostPutReqDto postPutReqDto) {
    Post post = postRepository.findById(postPutReqDto.getId())
        .orElseThrow(() -> new EntityNotFoundException(postPutReqDto.getId().toString()));
    post.update(postPutReqDto);
    return modelMapper.map(post, PostPutResDto.class);
  }

  @Transactional
  public Long delete(Long id) {
    Post post = postRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(id.toString()));
    postRepository.delete(post);
    return post.getId();
  }


}
