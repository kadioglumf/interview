package com.kadioglumf.interview.controller;

import com.kadioglumf.interview.annotations.IncomingLogger;
import com.kadioglumf.interview.enums.IncomingOperationNameEnum;
import com.kadioglumf.interview.model.dto.BookDto;
import com.kadioglumf.interview.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @IncomingLogger(operationName = IncomingOperationNameEnum.FIND_BOOKS_WITH_BY_TEXT)
    @GetMapping(value = "/{searchText}")
    public ResponseEntity<List<BookDto>> findBooksWithByText(@PathVariable(name = "searchText") String searchText) {
        List<BookDto> response = bookService.findBooksWithByText(searchText);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
