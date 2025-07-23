package com.playdata.backend.book.service.request;

import com.playdata.backend.book.entity.Book;
import com.playdata.backend.monoproj.account.entity.Account;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RegisterBookRequest {

    private final String title;
    private final String content;

    public Book toBook(Account account) {
        return new Book(title, content, account);
    }
}
