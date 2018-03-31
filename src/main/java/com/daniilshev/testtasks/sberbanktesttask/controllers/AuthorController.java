package com.daniilshev.testtasks.sberbanktesttask.controllers;

import com.daniilshev.testtasks.sberbanktesttask.entities.Author;
import com.daniilshev.testtasks.sberbanktesttask.entities.Book;
import com.daniilshev.testtasks.sberbanktesttask.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private BookRepository repository;

    @Autowired
    public AuthorController(BookRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}/books")
    public Page<Book> getBooksByAuthor(@PathVariable("id") Author author, Pageable pageable) {
        return repository.findByAuthor(author, pageable);
    }
}
