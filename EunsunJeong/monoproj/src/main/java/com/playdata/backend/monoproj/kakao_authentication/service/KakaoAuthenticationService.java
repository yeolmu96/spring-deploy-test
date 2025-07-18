package com.playdata.backend.monoproj.kakao_authentication.service;

import com.playdata.backend.monoproj.kakao_authentication.controller.response_form.KakaoUserInfoResponseForm;
import com.playdata.backend.monoproj.kakao_authentication.service.response.KakaoUserInfoResponse;

public interface KakaoAuthenticationService {
    KakaoUserInfoResponse handleLogin(String code);
}
