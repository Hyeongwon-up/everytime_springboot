package com.example.toy.src.sms.service;

import com.example.toy.src.user.repository.UserRepository;
import net.nurigo.java_sdk.api.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Service
public class SmsService {

    @Autowired
    UserRepository userRepository;

    @Transactional
    public void certifiedPhoneNumber(String phoneNumber, String cerNum) {

        String api_key = "NCSVIDSKCYMZDYMH";
        String api_secret = "SDGH5S3WLU8LTYDVNJHQUQPXAMAA90XN";
        Message coolsms = new Message(api_key, api_secret);

        // 4 params(to, from, type, text) are mandatory. must be filled
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", phoneNumber);    // 수신전화번호
        params.put("from", "발송할 번호 입력");    // 발신전화번호. 테스트시에는 발신,수신 둘다 본인 번호로 하면 됨
        params.put("type", "SMS");
        params.put("text", "휴대폰인증 테스트 메시지 : 인증번호는" + "["+cerNum+"]" + "입니다.");
        params.put("app_version", "test app 1.2"); // application name and version

//        try {
////            JSONObject obj = (JSONObject) coolsms.send(params);
////            System.out.println(obj.toString());
//        } catch (CoolsmsException e) {
////            System.out.println(e.getMessage());
////            System.out.println(e.getCode());
//        }

    }

}
