package com.daniilshev.testtasks.sberbanktesttask.controllers;

import com.daniilshev.testtasks.sberbanktesttask.entities.Book;
import com.daniilshev.testtasks.sberbanktesttask.entities.Publisher;
import com.daniilshev.testtasks.sberbanktesttask.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/publishers")
public class PublisherController {

    private BookRepository repository;

    @Autowired
    public PublisherController(BookRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}/books")
    public Page<Book> getBooksByPublisher(@PathVariable("id") Publisher publisher, Pageable pageable) {
        return repository.findByPublisher(publisher, pageable);
    }
}
