package com.playdata.backend.book.service;

import com.playdata.backend.book.entity.Book;
import com.playdata.backend.book.repository.BookRepository;
import com.playdata.backend.book.service.request.RegisterBookRequest;
import com.playdata.backend.book.service.response.RegisterBookResponse;
import com.playdata.backend.monoproj.account.entity.Account;
import com.playdata.backend.monoproj.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final AccountRepository accountRepository;
    private BookRepository bookRepository;

    public RegisterBookResponse register(RegisterBookRequest request, Long accountId){
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("account not found"));

        Book book = request.toBook(account);
        Book savedBook = bookRepository.save(book);

        return RegisterBookResponse.from(savedBook);
    }
}
