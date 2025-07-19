package com.playdata.backend.monoproj.account.service;

import com.playdata.backend.monoproj.account.controller.response_form.RegisterAccountResponseForm;
import com.playdata.backend.monoproj.account.entity.Account;
import com.playdata.backend.monoproj.account.repository.AccountRepository;
import com.playdata.backend.monoproj.account.service.request.RegisterAccountRequest;
import com.playdata.backend.monoproj.account.service.response.RegisterAccountResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{

    private final AccountRepository accountRepository;

    @Override
    public RegisterAccountResponseForm register(RegisterAccountRequest request) {
        Account requestedAccount = request.toAccount();
        Account savedAccount = accountRepository.save(requestedAccount);

        return RegisterAccountResponse.from(savedAccount);
    }
}
