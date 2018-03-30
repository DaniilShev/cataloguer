package com.daniilshev.testtasks.sberbanktesttask.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/authors")
public class AuthorController {
    @GetMapping("/{fullname}/books")
    public void getBooksByAuthor() {

    }
}
