package com.playdata.backend.monoproj.kakao_authentication.controller;

import com.playdata.backend.monoproj.kakao_authentication.service.KakaoAuthenticationService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/kakao-authentication")
public class KakaoAuthenticationController {

    private final KakaoAuthenticationService kakaoAuthenticationService;

    @GetMapping("login")
    @Transactional
    public String requestLogin(@RequestParam("code") String code, HttpServletResponse response) throws IOException {
        log.info("requestAccessToken(): code {}", code);

        return kakaoAuthenticationService.handleLogin(code);
    }
}


//         try{
//
//             Map<String, Object> tokenResponse = kakaoAuthenticationService.requestToken(code);
//             String accessToken = (String) tokenResponse.get("access_token");
//
//             Map<String, Object> userInfo = kakaoAuthenticationService.requestUserInfo(accessToken);
//             log.info("userInfo: {}", userInfo);
//
//             String nickname = (String) ((Map) userInfo.get("properties"))
//             String
//
//         }catch(Exception e){
//             log.error("Kakao 로그인 에러", e);
//             response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "카카오 로그인 실패: " + e.getMessage());
//         }