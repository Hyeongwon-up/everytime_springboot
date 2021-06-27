package com.example.toy.src.user.service;

import com.example.toy.config.BaseException;
import com.example.toy.config.BaseResponseStatus;
import com.example.toy.config.secret.Secret;
//import com.example.toy.src.post.entity.Post;
import com.example.toy.src.user.dto.LoginReqDto;
import com.example.toy.src.user.dto.PostUserReqDto;
import com.example.toy.src.user.entity.User;
import com.example.toy.src.user.repository.UserRepository;
import com.example.toy.utils.AES128;
import com.example.toy.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static com.example.toy.config.secret.Secret.JWT_SECRET_KEY;

import static com.example.toy.config.BaseResponseStatus.*;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private JwtService jwtService;
//    private AES128 aes128 = new AES128(JWT_SECRET_KEY);


    @Transactional
    public String createUser(PostUserReqDto postUserReqDto) throws BaseException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {

        if (userRepository.existsUserById(postUserReqDto.getId())) {
            throw new BaseException(OVERLAPPED_ID_FAIL);
        }
        if (userRepository.existsUserByNickname(postUserReqDto.getNickname())) {
            throw new BaseException(OVERLAPPED_NICKNAME_FAIL);
        }

        String encodingPassword = postUserReqDto.getPassword();
        encodingPassword = new AES128(Secret.USER_INFO_PASSWORD_KEY).encrypt(postUserReqDto.getPassword());

        User user = User.builder()
                .nickname(postUserReqDto.getNickname())
                .password(encodingPassword)
                .phone_num(postUserReqDto.getPhone_num())
                .univ_idx(postUserReqDto.getUniv_idx())
                .univ_year(postUserReqDto.getUniv_year())
                .user_email(postUserReqDto.getUser_email())
                .id(postUserReqDto.getId())
                .user_name(postUserReqDto.getUser_name())
                .build();

        userRepository.save(user);

        String accessToken = jwtService.createJwt(user.getUser_idx());
        return accessToken;
    }



    /**
     * 로그인
     */

    @Transactional
    public String login(LoginReqDto loginReqDto) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, BaseException {

        if (!userRepository.existsUserById(loginReqDto.getId())) {
            throw new BaseException(WRONG_ID);
        }
        String encodingPassword = new AES128(Secret.USER_INFO_PASSWORD_KEY).encrypt(loginReqDto.getPassword());

        User user = userRepository.findUserByIdAndPassword(loginReqDto.getId(), encodingPassword)
                .orElseThrow(() -> new BaseException(WRONG_LOGIN));

        return jwtService.createJwt(user.getUser_idx());
    }

}
