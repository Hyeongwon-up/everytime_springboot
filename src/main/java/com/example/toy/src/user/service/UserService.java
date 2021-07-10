package com.example.toy.src.user.service;

import com.example.toy.config.BaseException;
import com.example.toy.config.BaseResponseStatus;
import com.example.toy.config.secret.Secret;
import com.example.toy.src.user.dto.LoginReqDto;
import com.example.toy.src.user.dto.PostUserReqDto;
import com.example.toy.src.user.dto.SignUpReqDto;
import com.example.toy.src.user.dto.SignUpResDto;
import com.example.toy.src.user.entity.User;
import com.example.toy.src.user.repository.UserRepository;
//import com.example.toy.utils.JwtService;
import com.example.toy.utils.AES128;
import com.example.toy.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import static com.example.toy.config.BaseResponseStatus.*;


@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    private JwtService jwtService;

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

    public SignUpResDto registration(SignUpReqDto signUpReqDto) {
        User user = User.builder()
                .id(signUpReqDto.getUser_id())
                .password(signUpReqDto.getPassword())
                .univ_idx(signUpReqDto.getUniv_idx())
                .univ_year(signUpReqDto.getUniv_year())
                .nickname(signUpReqDto.getNickname())
                .user_name(signUpReqDto.getUser_name())
                .user_email(signUpReqDto.getUser_email())
                .phone_num(signUpReqDto.getPhone_num())
                .status(signUpReqDto.getStatus())
                .build();

        userRepository.save(user);

        SignUpResDto signUpResDto = SignUpResDto.builder()
                .user_name(signUpReqDto.getUser_name())
                .build();

        return signUpResDto;
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
