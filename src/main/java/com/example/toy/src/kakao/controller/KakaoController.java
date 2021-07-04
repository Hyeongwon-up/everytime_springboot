package com.example.toy.src.kakao.controller;
import com.example.toy.src.kakao.entity.KakaoAuth;
import com.example.toy.src.kakao.entity.KakaoUser;
import com.example.toy.src.kakao.service.KakaoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.example.toy.config.BaseResponseStatus.FAIL;
import static com.example.toy.config.BaseResponseStatus.SUCCESS;

@RestController
@Slf4j
public class KakaoController {

    @Autowired
    private KakaoService kakaoService;

    @GetMapping(value = "/kakao/callback")
    public @ResponseBody String kakaoCallback(String code){
        return "kakao code: " + code;
    }

    @PostMapping(value = "/kakao/token")
    public KakaoAuth kakaoToken(String code){
        KakaoAuth kakaoAuth = kakaoService.getAccessToken(code);
        return kakaoAuth;
    }

    @PostMapping(value = "/kakao/user/me")
    public KakaoUser kakaoUser(String accessToken){
        KakaoUser kakaoUserInfo = kakaoService.getKakaoUserInfo(accessToken);
        System.out.println("=============================출력(컨트롤러)==============");
//        log.debug("=============================출력(컨트롤러)==============");
        return kakaoUserInfo;
    }

}