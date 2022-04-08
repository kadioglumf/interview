package com.kadioglumf.interview.client;

import com.kadioglumf.interview.model.response.BookResponse;

public interface OpenLibraryClientFacade {

    BookResponse findBooksWithByText(String searchKey);
}
