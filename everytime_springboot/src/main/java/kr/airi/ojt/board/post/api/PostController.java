package kr.airi.ojt.board.post.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kr.airi.ojt.board.post.dto.PostGetDto;
import kr.airi.ojt.board.post.dto.PostPostDto.PostPostReqDto;
import kr.airi.ojt.board.post.dto.PostPostDto.PostPostResDto;
import kr.airi.ojt.board.post.dto.PostPutDto.PostPutReqDto;
import kr.airi.ojt.board.post.dto.PostPutDto.PostPutResDto;
import kr.airi.ojt.board.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
@Api(value = "Post")
public class PostController {

  private final PostService postService;

  @GetMapping
  @ApiOperation(value = "모든 또는 검색된 Post 가져오기")
  public ResponseEntity<Page<PostGetDto>> getAll(
      @RequestParam(required = false) String title,
      @RequestParam(required = false) String contents,
      @PageableDefault(size = 5) Pageable pageable) {

    return ResponseEntity.ok(postService.getAll(pageable, title, contents));
  }

  @GetMapping(value = "/{id}")
  @ApiOperation(value = "Post 가져오기")
  public ResponseEntity<PostGetDto> get(@PathVariable Long id) {
    return ResponseEntity.ok(postService.get(id));
  }

  @PostMapping
  @ApiOperation(value = "Post 만들기")
  public ResponseEntity<PostPostResDto> create(@RequestBody PostPostReqDto postPostReqDto) {
    return ResponseEntity.ok(postService.create(postPostReqDto));
  }

  @PutMapping
  @ApiOperation(value = "Post 변경하기")
  public ResponseEntity<PostPutResDto> update(@RequestBody PostPutReqDto postPutReqDto) {
    return ResponseEntity.ok(postService.update(postPutReqDto));
  }

  @DeleteMapping(value = "/{id}")
  @ApiOperation(value = "Post 삭제하기")
  public ResponseEntity<Long> delete(@PathVariable Long id) {
    return ResponseEntity.ok(postService.delete(id));
  }
}
