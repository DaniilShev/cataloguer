package com.daniilshev.testtasks.sberbanktesttask.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

@Controller
public class IndexController {

    @GetMapping("/")
    public ModelAndView getIndex() {
        return new ModelAndView("index", new HashMap<>(), HttpStatus.OK);
    }
}
