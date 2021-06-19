package com.example.toy.src.user.controller;

import com.example.toy.config.BaseException;
import com.example.toy.config.BaseResponse;
import static com.example.toy.config.BaseResponseStatus.*;
import com.example.toy.src.user.dto.PostUserReqDto;
import com.example.toy.src.user.service.UserService;
import com.example.toy.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;
    private PostUserReqDto postUserReqDto;
    private JwtService jwtService;

    @PostMapping("")
    public BaseResponse<String> createUser(@RequestBody PostUserReqDto postUserReqDto) throws BaseException {
        String tmp = userService.createUser(postUserReqDto);
        return new BaseResponse<>(SUCCESS, tmp);
    }

}
