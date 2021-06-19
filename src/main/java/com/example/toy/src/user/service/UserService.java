package com.example.toy.src.user.service;

import com.example.toy.config.BaseException;
import com.example.toy.config.BaseResponseStatus;
import com.example.toy.src.user.dto.PostUserReqDto;
import com.example.toy.src.user.entity.User;
import com.example.toy.src.user.repository.UserRepository;
//import com.example.toy.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
//    private final JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

//    @Transactional
    public String createUser(PostUserReqDto postUserReqDto) throws BaseException {
        User user = User.builder()
                .user_idx(postUserReqDto.getUser_idx())
                .user_id(postUserReqDto.getUser_id())
                .password(postUserReqDto.getPassword())
                .univ_idx(postUserReqDto.getUniv_idx())
                .univ_year(postUserReqDto.getUniv_year())
                .nickname(postUserReqDto.getNickname())
                .user_name(postUserReqDto.getUser_name())
                .user_email(postUserReqDto.getUser_email())
                .phone_num(postUserReqDto.getPhone_num())
                .status(postUserReqDto.getStatus())
                .build();

        try {
            userRepository.save(user);
        } catch (Exception exception){
            throw new BaseException(BaseResponseStatus.FAIL);
        }

        return "Success Creating User";
    }
}
