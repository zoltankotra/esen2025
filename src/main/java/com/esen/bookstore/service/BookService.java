package com.esen.bookstore.service;

import com.esen.bookstore.model.Book;
import com.esen.bookstore.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

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

    public void delete(Long id) {
        if (!booksRepository.existsById(id)) {
            throw new IllegalArgumentException("Cannot find book with id " + id);
        }

        booksRepository.deleteById(id);
    }

    public Book update(Long id, String title, String author, String publisher, Double price) {
        if (Stream.of(title, author, publisher, price).allMatch(Objects::isNull)) {
            throw new IllegalArgumentException("At leas one input is required");
        }

        if (!booksRepository.existsById(id)) {
            throw new IllegalArgumentException("Cannot find book with id " + id);
        }

        var book = booksRepository.findById(id).get();

        if (title != null) {
            book.setTitle(title);
        }

        if (author != null) {
            book.setAuthor(author);
        }

        if (publisher != null) {
            book.setPublisher(publisher);
        }

        if (price != null) {
            book.setPrice(price);
        }
        return book;
    }
}
