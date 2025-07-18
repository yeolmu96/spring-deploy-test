package com.playdata.backend.monoproj.account.controller.request_form;

import com.playdata.backend.monoproj.account.service.request.RegisterAccountRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RegisterAccountRequestForm {

    private final String email;
    private final String nickname;

    public RegisterAccountRequest toRegisterAccountRequest(){
        return new RegisterAccountRequest(email, nickname);
    }
}
