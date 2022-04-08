package com.kadioglumf.interview.service.impl;

import com.kadioglumf.interview.client.OpenLibraryClientFacade;
import com.kadioglumf.interview.entity.BookEntity;
import com.kadioglumf.interview.mapper.BookMapper;
import com.kadioglumf.interview.model.dto.BookDto;
import com.kadioglumf.interview.model.response.BookResponse;
import com.kadioglumf.interview.model.dto.UserDto;
import com.kadioglumf.interview.repository.BookRepository;
import com.kadioglumf.interview.service.BookService;
import com.kadioglumf.interview.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final OpenLibraryClientFacade openLibraryClientFacade;
    private final UserService userService;
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public List<BookDto> findBooksWithByText(String searchText) {
        BookResponse response = openLibraryClientFacade.findBooksWithByText(searchText);
        return bookMapper.toListBookDto(response.getDocs());
    }
}
