package com.esen.bookstore.service;

import com.esen.bookstore.model.Book;
import com.esen.bookstore.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository booksRepository;

    public void save(Book book) {
        booksRepository.save(book);
    }

    public List<Book> findAll() {
        return booksRepository.findAll();
    }
}
