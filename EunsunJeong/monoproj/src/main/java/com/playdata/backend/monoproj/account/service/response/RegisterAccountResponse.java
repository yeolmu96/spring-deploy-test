package com.playdata.backend.monoproj.account.service.response;

import com.playdata.backend.monoproj.account.entity.Account;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RegisterAccountResponse {
    final private Long accountId;
    final private String email;
    final private String nickname;

    public static RegisterAccountResponse from(final Account account) {
        return new RegisterAccountResponse(
                account.getId(),
                account.getEmail(),
                account.getNickname());
    }
}
