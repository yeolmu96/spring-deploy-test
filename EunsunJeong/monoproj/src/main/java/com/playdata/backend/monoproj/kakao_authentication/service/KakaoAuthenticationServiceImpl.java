package com.playdata.backend.monoproj.kakao_authentication.service;

import com.playdata.backend.monoproj.account.entity.Account;
import com.playdata.backend.monoproj.account.repository.AccountRepository;
import com.playdata.backend.monoproj.kakao_authentication.controller.response_form.KakaoUserInfoResponseForm;
import com.playdata.backend.monoproj.kakao_authentication.repository.KakaoAuthenticationRepository;
import com.playdata.backend.monoproj.kakao_authentication.service.response.KakaoUserInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KakaoAuthenticationServiceImpl implements KakaoAuthenticationService {
    final private KakaoAuthenticationRepository kakaoAuthenticationRepository;
    private final AccountRepository accountRepository;

    @Override
    public KakaoUserInfoResponse handleLogin(String code) {
        // Map<String, Object> getAccessToken(String code);
        Map<String, Object> tokenResponse = kakaoAuthenticationRepository.getAccessToken(code);
        String accessToken = tokenResponse.get("access_token").toString();

        // Map<String, Object> getUserInfo(String accessToken);
        Map<String, Object> userInfoResponse = kakaoAuthenticationRepository.getUserInfo(accessToken);
        Map<?, ?> userInfoProperties = (Map<?, ?>) userInfoResponse.get("properties");
        String nickname = (String) (userInfoProperties).get("nickname");

        Map<?, ?> userInfoKakaoAccount = (Map<?, ?>) userInfoResponse.get("kakao_account");
        String email = (String) (userInfoKakaoAccount).get("email");

        //기존, 신규 회원인지 확인
        Optional<Account> exist = accountRepository.findByEmail(email);
        if(exist.isEmpty()){
//            throw new IllegalArgumentException("신규 회원입니다.");

            //회원가입 시키기
            Account newAccount = new Account(email, nickname);
            accountRepository.save(newAccount);
        }

        return KakaoUserInfoResponse.from(email, nickname, accessToken);
    }
}
