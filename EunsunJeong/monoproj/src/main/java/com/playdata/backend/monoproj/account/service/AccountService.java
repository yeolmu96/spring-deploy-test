package com.playdata.backend.monoproj.account.service;

import com.playdata.backend.monoproj.account.controller.response_form.RegisterAccountResponseForm;
import com.playdata.backend.monoproj.account.service.request.RegisterAccountRequest;

public interface AccountService {
    RegisterAccountResponseForm register(RegisterAccountRequest request);
}