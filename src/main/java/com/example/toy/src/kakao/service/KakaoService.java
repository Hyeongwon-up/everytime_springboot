package com.example.toy.src.kakao.service;

import com.example.toy.src.kakao.entity.KakaoAuth;
import com.example.toy.src.kakao.entity.KakaoUser;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class KakaoService {

    private final static String K_CLIENT_ID = "0a60ef120975fdf6d6555a4cdaa3d497";
    private final static String K_REDIRECT_URI = "http://localhost:8880/kakao/callback";


    // GET https://kauth.kakao.com/oauth/authorize?client_id={REST_API_KEY}&redirect_uri={REDIRECT_URI}&response_type=code
    public String getAutorizationUrl(){
        String kakaoUrl = "https://kauth.kakao.com/oauth/authorize?client_id="
        + K_CLIENT_ID + "redirect_uri=" + K_REDIRECT_URI + "&response_type=code";
        return kakaoUrl;
    }

    // POST "https://kauth.kakao.com/oauth/token"
    // Content-type: application/x-www-form-urlencoded;charset=utf-8

    //    {body}
    //    "grant_type=authorization_code"
    //    "client_id={REST_API_KEY}"
    //    "redirect_uri={REDIRECT_URI}"
    //    "code={AUTHORIZATION_CODE}"

    private final static String K_TOKEN_URI = "https://kauth.kakao.com/oauth/token";

    public KakaoAuth getAccessToken(String code){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        // 파라미터를 넘기는데, map으로 키, 밸류 형태로 넘겨줌
        params.add("grant_type", "authorization_code");
        params.add("client_id", K_CLIENT_ID);
        params.add("redirect_uri", K_REDIRECT_URI);
        params.add("code", code);

        HttpEntity<MultiValueMap<String, String>> accessTokenRequest = new HttpEntity<>(params,headers);
        // 헤더와 파라미터 합치는 과정

        ResponseEntity<String> response = restTemplate.postForEntity(K_TOKEN_URI, accessTokenRequest, String.class);
        // String.class: String으로 보내겠다.
        // postForEntity: post방식, K_TOKEN_URI로 accessTokenRequest를 보낸다.

        // Gson, Json Simple, ObjectMapper
        Gson gson = new Gson();
        if(response.getStatusCode() == HttpStatus.OK)
        {
            return gson.fromJson(response.getBody(), KakaoAuth.class);
        }
        return null;

    }

    //    GET/POST https://kapi.kakao.com/v2/user/me HTTP/1.1
    //    Authorization: Bearer {ACCESS_TOKEN}
    //    Content-type: application/x-www-form-urlencoded;charset=utf-8

    private final static String K_USER_URI = "https://kapi.kakao.com/v2/user/me";

    public KakaoUser getKakaoUserInfo(String accessToken){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        headers.add("Authorization", "Bearer "+ accessToken);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(K_USER_URI, requestEntity, String.class);

        // Gson, Json Simple, ObjectMapper 세 가지 정도의 클래스가 있음
        Gson gson = new Gson();
        if(response.getStatusCode() == HttpStatus.OK)
        {
            return gson.fromJson(response.getBody(), KakaoUser.class);
        }
        return null;
    }

}
