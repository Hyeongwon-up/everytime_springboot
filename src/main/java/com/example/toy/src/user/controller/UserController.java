package com.example.toy.src.user.controller;

import com.example.toy.config.BaseException;
import com.example.toy.config.BaseResponse;
import static com.example.toy.config.BaseResponseStatus.*;

//import com.example.toy.src.board.entity.Board;
import com.example.toy.config.BaseResponseStatus;
import com.example.toy.src.user.dto.LoginReqDto;
import com.example.toy.src.user.dto.PostUserReqDto;
import com.example.toy.src.user.service.UserService;
import com.example.toy.utils.JwtService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;

    @PostMapping("/users")
    public BaseResponse<String> createUser(@RequestBody PostUserReqDto postUserReqDto) throws BaseException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {

        try {
            return new BaseResponse<>(SUCCESS_SIGN_UP, userService.createUser(postUserReqDto));
        } catch (BaseException e) {
                return new BaseResponse<>(e.getStatus());
        }
    }

    @PostMapping("/login")
    public BaseResponse<String> login(@RequestBody LoginReqDto loginReqDto) throws BaseException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        try {
            return new BaseResponse<>(SUCCESS_LOGIN, userService.login(loginReqDto));
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }


    @GetMapping("/users/mine")
    @ApiOperation(value = "유저 인덱스 조회")
    public BaseResponse<Long> getUserIdx(@RequestHeader("X-ACCESS-TOKEN") String token) throws BaseException {

        long tokenUserId;
        tokenUserId = jwtService.getUser_idx();

        return new BaseResponse<>(SUCCESS, tokenUserId);
    }

}
