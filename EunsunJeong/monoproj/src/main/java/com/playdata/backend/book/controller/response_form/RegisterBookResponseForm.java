package com.playdata.backend.book.controller.response_form;

import com.playdata.backend.book.service.response.RegisterBookResponse;

public class RegisterBookResponseForm {
    private String title;
    private String content;
    private Long accountId;

    public static RegisterBookResponseForm from(RegisterBookResponse response) {
        RegisterBookResponseForm form = new RegisterBookResponseForm();
        form.setTitle(response.getTitle());
        form.setContent(response.getContent());
        form.setAccountId(response.getAccountId());
        return form;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
}
