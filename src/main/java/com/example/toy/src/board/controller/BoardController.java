package com.example.toy.src.board.controller;

import com.example.toy.config.BaseResponse;
import com.example.toy.src.board.dto.PostBoardReqDto;
import com.example.toy.src.board.entity.Board;
import com.example.toy.src.board.repository.BoardRepository;
import com.example.toy.src.board.service.BoardService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.toy.config.BaseResponseStatus.SUCCESS;

@RestController
public class BoardController {
    @Autowired
    private BoardService boardService;

    @PostMapping("/board")
    @ApiOperation(value = "게시판 생성")
    public BaseResponse<String> create(@RequestBody PostBoardReqDto postBoardReqDto){
        String tmp = boardService.createBoard(postBoardReqDto);
        return new BaseResponse<>(SUCCESS);
    }

    @GetMapping("/board")
    @ApiOperation(value = "게시판 전체 조회")
    public BaseResponse<List<Board>> getAllBoards(){
        List<Board> tmp = boardService.selectAllBoards();
        return new BaseResponse<>(SUCCESS, tmp);
    }
}
