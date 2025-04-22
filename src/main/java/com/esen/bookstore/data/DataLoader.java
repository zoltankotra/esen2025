package com.esen.bookstore.data;

import com.esen.bookstore.model.Book;
import com.esen.bookstore.model.Bookstore;
import com.esen.bookstore.repository.BookRepository;
import com.esen.bookstore.repository.BookstoreRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Component
@Slf4j
@Data
public class DataLoader {


    private final BookRepository bookRepository;
    private final BookstoreRepository bookstoreRepository;

    @Value("classpath:data/books.json")
    private Resource booksResource;

    @Value("classpath:data/bookstores.json")
    private Resource bookstoreResource;

    @PostConstruct
    public void loadData() {
        var objectMapper = new ObjectMapper();

        try {
            var booksJson = StreamUtils.copyToString(booksResource.getInputStream(), StandardCharsets.UTF_8);
            var books = objectMapper.readValue(booksJson, new TypeReference<List<Book>>(){});

            var bookstoreJson = StreamUtils.copyToString(bookstoreResource.getInputStream(), StandardCharsets.UTF_8);
            var bookstores = objectMapper.readValue(bookstoreJson, new TypeReference<List<Bookstore>>(){});


            bookstores.forEach(bookstore ->  {
                bookstore.setInventory(books.stream()
                        .collect(Collectors.toMap(
                                book -> book,
                                book -> ThreadLocalRandom.current().nextInt(1,50)
                        )));
            });

            bookRepository.saveAll(books);
            bookstoreRepository.saveAll(bookstores);


        } catch (IOException e) {
            System.out.println("Cannot seed database");
        }
    }
}






