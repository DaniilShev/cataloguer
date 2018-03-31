package com.daniilshev.testtasks.sberbanktesttask.controllers;

import com.daniilshev.testtasks.sberbanktesttask.entities.Book;
import com.daniilshev.testtasks.sberbanktesttask.entities.Publisher;
import com.daniilshev.testtasks.sberbanktesttask.repositories.BookRepository;
import com.daniilshev.testtasks.sberbanktesttask.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/publishers")
public class PublisherController {

    private PublisherRepository publisherRepository;
    private BookRepository bookRepository;

    @Autowired
    public PublisherController(PublisherRepository publisherRepository, BookRepository bookRepository) {
        this.publisherRepository = publisherRepository;
        this.bookRepository = bookRepository;
    }

    @GetMapping("/")
    public List<Publisher> getAuthors() {
        return publisherRepository.findAll();
    }

    @GetMapping("/{id}/books")
    public Page<Book> getBooksByPublisher(@PathVariable("id") Publisher publisher, Pageable pageable) {
        return bookRepository.findByPublisher(publisher, pageable);
    }
}
