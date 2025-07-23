package com.playdata.backend.book.controller.request_form;

import com.playdata.backend.book.service.request.RegisterBookRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class RegisterBookRequestForm {
    private final String title;
    private final String content;

    public RegisterBookRequest toRegisterBookRequest() {
        return new RegisterBookRequest(title, content);
    }
}
