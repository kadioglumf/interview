package com.kadioglumf.interview.repository;

import com.kadioglumf.interview.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
}
