package com.daniilshev.testtasks.sberbanktesttask;

import com.daniilshev.testtasks.sberbanktesttask.entities.Author;
import com.daniilshev.testtasks.sberbanktesttask.entities.Book;
import com.daniilshev.testtasks.sberbanktesttask.entities.Publisher;
import com.daniilshev.testtasks.sberbanktesttask.repositories.AuthorRepository;
import com.daniilshev.testtasks.sberbanktesttask.repositories.BookRepository;
import com.daniilshev.testtasks.sberbanktesttask.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class SberbankTesttaskApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SberbankTesttaskApplication.class);
    }

	public static void main(String[] args) {
		SpringApplication.run(SberbankTesttaskApplication.class, args);
	}

    @Bean
    public CommandLineRunner demo(AuthorRepository authorRepository,
                                  PublisherRepository publisherRepository,
                                  BookRepository bookRepository) {
        return (args) -> {
            Author rowling = authorRepository.save(new Author("J. K. Rowling", "1965", "United Kingdom"));
            Publisher bloomsbury = publisherRepository.save(new Publisher("Bloomsbury Publishing","United Kingdom"));
            bookRepository.save(new Book("Harry Potter and the Philosopher's Stone", bloomsbury, rowling, 10, 1, true));
            bookRepository.save(new Book("Harry Potter and the Chamber of Secrets", bloomsbury, rowling, 9, 2, false));
            bookRepository.save(new Book("Harry Potter and the Prisoner of Azkaban", bloomsbury, rowling, 6, 14, true));
            bookRepository.save(new Book("Harry Potter and the Goblet of Fire", bloomsbury, rowling, 1, 4, false));
            bookRepository.save(new Book("Harry Potter and the Order of the Phoenix", bloomsbury, rowling, 5, 3, true));
            bookRepository.save(new Book("Harry Potter and the Half-Blood Prince", bloomsbury, rowling, 3, 7, true));
            bookRepository.save(new Book("Harry Potter and the Deathly Hallows", bloomsbury, rowling, 10, 3, false));

            Author grossman = authorRepository.save(new Author("Lev Grossman", "1969", "USA"));
            Publisher penguin = publisherRepository.save(new Publisher("Penguin Books", "United kingdom"));
            bookRepository.save(new Book("The Magicians", penguin, grossman, 9, 5, false));
            bookRepository.save(new Book("The Magician King", penguin, grossman, 6, 1, true));
            bookRepository.save(new Book("The Magician's Land", penguin, grossman, 9, 7, false));
        };
    }
}
