package com.example.toy.src.board.service;

import com.example.toy.src.board.dto.PostBoardReqDto;
import com.example.toy.src.board.entity.Board;
import com.example.toy.src.board.repository.BoardRepository;
import com.example.toy.src.post.repository.PostRepository;
import com.example.toy.src.user.entity.User;
import com.example.toy.src.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private UserRepository userRepository;


    @Transactional
    public String createBoard(PostBoardReqDto postBoardReqDto){

        User user = userRepository.findById(postBoardReqDto.getUser_idx()).get();
//        Integer univ_idx = user.getUniv_idx();

        Board board = Board.builder()
                .board_name(postBoardReqDto.getBoard_name())
                .user_idx(postBoardReqDto.getUser_idx())
                .univ_idx(user.getUniv_idx())
                .build();

        boardRepository.save(board);

        return "Success";
//
    }

    @Transactional
    public List<Board> selectAllBoards(){
        List<Board> allBoards= boardRepository.findAll();
        return allBoards;
    }


}
