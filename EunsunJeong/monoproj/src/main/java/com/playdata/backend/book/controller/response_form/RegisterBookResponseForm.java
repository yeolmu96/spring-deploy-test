package com.playdata.backend.book.controller.response_form;

import com.playdata.backend.book.service.response.RegisterBookResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RegisterBookResponseForm {
    private final Long bookId;
    private final String title;
    private final String content;
    private final Long registeredAccountNickname;

    public static RegisterBookResponseForm from(RegisterBookResponse response) {
        return new RegisterBookResponseForm(
                response.getBookId(),
                response.getTitle(),
                response.getContent(),
                response.getRegisteredAccountNickname()
        );
    }
}
