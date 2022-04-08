package com.kadioglumf.interview.service;

import com.kadioglumf.interview.model.dto.BookDto;

import java.util.List;

public interface BookService {

    List<BookDto> findBooksWithByText(String searchText);
}
