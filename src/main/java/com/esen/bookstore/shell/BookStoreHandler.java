package com.esen.bookstore.shell;


import com.esen.bookstore.model.Book;
import com.esen.bookstore.model.Bookstore;
import com.esen.bookstore.service.BookstoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.stream.Collectors;

@ShellComponent
@ShellCommandGroup("Bookstore related commands")
@RequiredArgsConstructor
public class BookStoreHandler {

    private final BookstoreService bookstoreService;

    @ShellMethod(value = "List all bookstores", key = "list bookstores")
    public String listBooks() {
        return bookstoreService.findAll()
                .stream()
                .map(bookstore -> {
                    bookstore.setInventory(null);
                    return bookstore;
                })
                .map(Bookstore::toString)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    @ShellMethod(value = "Deletes a bookstore by ID", key = "delete bookstore")
    public void deleteBookstore(Long id) {
        bookstoreService.delete(id);
    }

    @ShellMethod(value = "Creates a bookstore", key = "create bookstore")
    public void createBookstore(String location, Double priceModifier, Double moneyInCashRegister) {
        bookstoreService.save(Bookstore.builder()
                .location(location)
                .priceModifier(priceModifier)
                .moneyInCashRegister(moneyInCashRegister)
                .build());

    }

    @ShellMethod(value = "Updates a bookstore", key = "update bookstore")
    public Bookstore updateBookstore(@ShellOption(defaultValue = ShellOption.NULL) Long id,
                           @ShellOption(defaultValue = ShellOption.NULL) String location,
                           @ShellOption(defaultValue = ShellOption.NULL) Double priceModifier,
                           @ShellOption(defaultValue = ShellOption.NULL) Double moneyInCashRegister) {
        return bookstoreService.update(id, location, priceModifier, moneyInCashRegister);
    }
}