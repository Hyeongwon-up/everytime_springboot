package kr.airi.ojt.board.comment.api;

import io.swagger.annotations.Api;
import kr.airi.ojt.board.comment.dto.CommentPostDto.CommentPostReqDto;
import kr.airi.ojt.board.comment.dto.CommentPostDto.CommentPostResDto;
import kr.airi.ojt.board.comment.dto.CommentPutDto.CommentPutReqDto;
import kr.airi.ojt.board.comment.dto.CommentPutDto.CommentPutResDto;
import kr.airi.ojt.board.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("comment")
@Api(value = "Comment")
public class CommentController {

  private final CommentService commentService;

  @PostMapping
  public ResponseEntity<CommentPostResDto> post(@RequestBody CommentPostReqDto reqDto) {
    log.debug("reqDto: {}", reqDto.toString());
    return ResponseEntity.ok(commentService.post(reqDto));
  }

  @PutMapping(value = "/{commentId}")
  public ResponseEntity<CommentPutResDto> put(@PathVariable Long commentId,
      @RequestBody CommentPutReqDto reqDto) {
    log.debug("commentId: {}, content: {}", commentId, reqDto);
    return ResponseEntity.ok(commentService.put(commentId, reqDto));
  }

  @DeleteMapping(value = "/{commentId}")
  public ResponseEntity<Long> delete(@PathVariable Long commentId) {
    log.debug("commentId: {}", commentId);
    return ResponseEntity.ok(commentService.delete(commentId));
  }
}
