package com.playdata.backend.monoproj.account.controller;

import com.playdata.backend.monoproj.account.controller.request_form.RegisterAccountRequestForm;
import com.playdata.backend.monoproj.account.controller.response_form.RegisterAccountResponseForm;
import com.playdata.backend.monoproj.account.repository.AccountRepository;
import com.playdata.backend.monoproj.account.service.AccountService;
import com.playdata.backend.monoproj.account.service.request.RegisterAccountRequest;
import com.playdata.backend.monoproj.account.service.response.RegisterAccountResponse;
import com.playdata.backend.monoproj.redis_cache.RedisCacheService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;
    private final RedisCacheService redisCacheService;

    @PostMapping("/register")
    public RegisterAccountResponseForm createAccount(
            @RequestHeader("Authorization") String authorizedHeader,
            @RequestBody RegisterAccountRequestForm requestForm
    ){

        log.info("createAccount() -> requestForm: {}", requestForm);

        //임시 토큰 가지고 있는지 확인
        String temporaryUserToken = authorizedHeader.replace("Bearer ", "").trim();
        String accessToken = redisCacheService.getValueByKey(temporaryUserToken, String.class);

        if(accessToken == null){
            throw new IllegalArgumentException("만료되었거나 잘못된 토큰 요청입니다.");
        }

        //회원 가입 진행
        RegisterAccountRequest request = requestForm.toRegisterAccountRequest();
        RegisterAccountResponse response = accountService.register(request);
        Long accountId = response.getAccountId();

        //진짜 토큰 발급
        String userToken = UUID.randomUUID().toString();
        redisCacheService.setKeyAndValue(userToken, accountId);
        redisCacheService.setKeyAndValue(accountId, accessToken);

        //임시 토큰 삭제
        redisCacheService.deleteByKey(temporaryUserToken);

        return RegisterAccountResponseForm.from(response, userToken);
    }
}
