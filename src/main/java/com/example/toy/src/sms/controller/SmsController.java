package com.example.toy.src.sms.controller;

import com.example.toy.config.BaseException;
import com.example.toy.config.BaseResponse;
import com.example.toy.src.sms.service.SmsService;
import com.example.toy.src.user.dto.PostUserReqDto;
import com.example.toy.src.user.service.UserService;
import com.example.toy.utils.JwtService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import static com.example.toy.config.BaseResponseStatus.SUCCESS_SIGN_UP;

@RestController
public class SmsController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private SmsService smsService;

    @GetMapping("/check/sendSMS")
    public String sendSMS(String phoneNumber){
//        try {
        Random rand = new Random();
        String numStr = "";

        for(int i = 0; i<4; i++){
            String ran = Integer.toString(rand.nextInt(10));
            numStr+=ran;
        }
        System.out.println("수신자 번호 : " + phoneNumber);
        System.out.println("인증번호 : " + numStr);

//        return "Success";
        smsService.certifiedPhoneNumber(phoneNumber,numStr);
        return numStr;
//        } catch (BaseException e) {
//            return new BaseResponse<>(e.getStatus());
//        }
    }
}
