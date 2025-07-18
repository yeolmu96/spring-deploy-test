package com.playdata.backend.monoproj.account.controller;

import com.playdata.backend.monoproj.account.controller.request.RegisterAccountRequest;
import com.playdata.backend.monoproj.account.controller.response_form.RegisterAccountResponse;
import com.playdata.backend.monoproj.account.service.AccountService;
import com.playdata.backend.monoproj.redis_cache.RedisCacheService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;
    private final RedisCacheService redisCacheService;

    @PostMapping("/register")
    public RegisterAccountResponse createAccount(){

        //회원가입 진행
        RegisterAccountRequest request = requestForm.toRegisterAccountReqeust();
        RegisterAccountResponse response = accountService.register(request);
        Long accountId = response.getAccountId();

        //진짜 토큰 발급
        String userToken = UUID.randomUUID().toString();
        redisCacheService.setKeyAndValue(userToken, accountId);
        redisCacheService.setKeyAndValue(accountId, accessToken);

        //임시 토큰 삭제
        redisCacheService.deleteKey(temporaryUserToken);

        return RegisterAccountResponse.from(response);
    }
}
