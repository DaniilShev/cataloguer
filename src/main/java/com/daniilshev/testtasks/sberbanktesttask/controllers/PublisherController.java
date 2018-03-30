package com.daniilshev.testtasks.sberbanktesttask.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/publishers")
public class PublisherController {
    @GetMapping("/{name}/books")
    public void getBooksByPublisher() {

    }


}
