package com.playdata.backend.monoproj.account.controller.response_form;

import com.playdata.backend.monoproj.account.controller.request_form.RegisterAccountRequestForm;
import com.playdata.backend.monoproj.account.service.response.RegisterAccountResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RegisterAccountResponseForm {

    final private Long accountId;
    final private String email;
    final private String nickname;

    pubilc static RegisterAccountRequestForm from(
            final RegisterAccountResponse response
            ){

        return new RegisterAccountRequestForm(
                response.getAccountId(),
                response.getEmail(),
                response.getNickname()
        );
    }
}
