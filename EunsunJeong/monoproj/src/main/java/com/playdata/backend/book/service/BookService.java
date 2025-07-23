package com.playdata.backend.book.service;

import com.playdata.backend.book.service.request.RegisterBookRequest;
import com.playdata.backend.book.service.response.RegisterBookResponse;

public interface BookService {
    RegisterBookResponse register(RegisterBookRequest request, Long accountId);
}
