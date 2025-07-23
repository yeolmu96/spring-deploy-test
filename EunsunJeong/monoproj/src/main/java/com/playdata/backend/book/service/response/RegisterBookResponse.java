package com.playdata.backend.book.service.response;

import com.playdata.backend.book.entity.Book;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RegisterBookResponse {
    private final String title;
    private final String content;
    private final Long accountId;

    public static RegisterBookResponse from(Book book) {
        return new RegisterBookResponse(
                book.getTitle(),
                book.getContent(),
                book.getAccountId().getId()
        );
    }
}