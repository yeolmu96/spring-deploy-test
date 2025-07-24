package com.playdata.backend.book.service.response;

import com.playdata.backend.book.entity.Book;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RegisterBookResponse {
    final private Long bookId;
    final private String title;
    final private String content;
    final private String registeredAccountNickname;

    public static RegisterBookResponse from(final Book book) {
        return new RegisterBookResponse(
                book.getId(),
                book.getTitle(),
                book.getContent(),
                book.getAccount().getNickname());
    }
}