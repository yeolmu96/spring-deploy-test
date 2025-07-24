package com.playdata.backend.book.service;

import com.playdata.backend.book.entity.Book;
import com.playdata.backend.book.repository.BookRepository;
import com.playdata.backend.book.service.request.RegisterBookRequest;
import com.playdata.backend.book.service.response.RegisterBookResponse;
import com.playdata.backend.monoproj.account.entity.Account;
import com.playdata.backend.monoproj.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final AccountRepository accountRepository;
    private BookRepository bookRepository;

    public RegisterBookResponse register(RegisterBookRequest request, Long accountId){
        // findById로 사용자 찾기 (등록자)
        Optional<Account> maybeAccount = accountRepository.findById(accountId);
        if (maybeAccount.isEmpty()) {
            log.info("요청한 account 존재하지 않음");
            return null;
        }

        Account account = maybeAccount.get();
        Book requestedBook = request.toBook(account);

        Book savedBook = bookRepository.save(requestedBook);
        return RegisterBookResponse.from(savedBook);
    }
}
