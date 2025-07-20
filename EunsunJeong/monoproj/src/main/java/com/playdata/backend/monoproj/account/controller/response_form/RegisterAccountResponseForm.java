package com.playdata.backend.monoproj.account.controller.response_form;

import com.playdata.backend.monoproj.account.controller.request_form.RegisterAccountRequestForm;
import com.playdata.backend.monoproj.account.service.response.RegisterAccountResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RegisterAccountResponseForm {

    final private String userToken;
    final private String email;
    final private String nickname;

    public static RegisterAccountResponseForm from(
            final RegisterAccountResponse response,
            final String userToken
            ){

        return new RegisterAccountResponseForm(
                userToken,
                response.getEmail(),
                response.getNickname()
        );
    }
}
