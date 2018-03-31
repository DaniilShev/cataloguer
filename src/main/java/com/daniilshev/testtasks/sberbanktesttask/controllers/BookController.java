package com.daniilshev.testtasks.sberbanktesttask.controllers;

import com.daniilshev.testtasks.sberbanktesttask.entities.Book;
import com.daniilshev.testtasks.sberbanktesttask.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {

    private BookRepository repository;

    @Autowired
    public BookController(BookRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public Page<Book> getAllBooks(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @GetMapping("/search")
    public Page<Book> searchBooks(@RequestParam(value="q", defaultValue="") String query, Pageable pageable) {
        return repository.findByNameOrAuthorFullnameOrPublisherNameContainsAllIgnoreCase(query, query, query, pageable);
    }
}
