package com.playdata.backend.monoproj.kakao_authentication.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@Repository
public class KakaoAuthenticationRepositoryImpl implements KakaoAuthenticationRepository {

    private final String clientId;
    private final String redirectUri;
    //client가 되어 요청하기 위해 필요함
    private final RestTemplate restTemplate;
    private final String tokenRequestUri;

    public KakaoAuthenticationRepositoryImpl(
            @Value("${kakao.client-id}") String clientId,
            @Value("${kakao.redirect-uri}") String redirectUri,
            @Value("${kakao.token-request-uri}") String tokenRequestUri,
            RestTemplate restTemplate) {

        //yaml파일의 설정값이 들어감
        this.clientId = clientId;
        this.redirectUri = redirectUri;
        this.tokenRequestUri = tokenRequestUri;
        this.restTemplate = restTemplate;
    }

    @Override
    public Map<String, Object> getAccessToken(String code) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        params.add("grant_type", "authorization_code");
        params.add("client_id", clientId);
        params.add("redirect_uri", redirectUri);
        params.add("code", code);
        params.add("client_secret", "");

        HttpHeaders headers = new HttpHeaders();

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
        ResponseEntity<Map> response = restTemplate.exchange(tokenRequestUri, HttpMethod.POST, requestEntity, Map.class);

        return response.getBody();
    }

}
