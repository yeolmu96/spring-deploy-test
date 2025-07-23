package com.playdata.backend.book.controller;

import com.playdata.backend.book.controller.request_form.RegisterBookRequestForm;
import com.playdata.backend.book.controller.response_form.RegisterBookResponseForm;
import com.playdata.backend.book.service.BookServiceImpl;
import com.playdata.backend.book.service.request.RegisterBookRequest;
import com.playdata.backend.book.service.response.RegisterBookResponse;
import com.playdata.backend.monoproj.redis_cache.RedisCacheService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {

    private final BookServiceImpl bookService;
    private final RedisCacheService redisCacheService;

    //사용자가 책을 등록
    @PostMapping("register")
    public RegisterBookResponseForm registerBook(@RequestHeader("Authorization") String token,
                                                  @RequestBody RegisterBookRequestForm requestForm){

        log.info("registerBook() -> request={}", requestForm);

        String userToken = token.replace("Bearer ", "");
        Long accountId = redisCacheService.getValueByKey(userToken, Long.class);

        RegisterBookRequest request = requestForm.toRegisterBookRequest();
        RegisterBookResponse response = bookService.register(request, accountId);

        return RegisterBookResponseForm.from(response);
    }
}
