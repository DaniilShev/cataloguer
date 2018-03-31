package com.daniilshev.testtasks.sberbanktesttask.repositories;

import com.daniilshev.testtasks.sberbanktesttask.entities.Author;
import com.daniilshev.testtasks.sberbanktesttask.entities.Book;
import com.daniilshev.testtasks.sberbanktesttask.entities.Publisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Page<Book> findAll(Pageable pageable);
    Page<Book> findByAuthor(Author author, Pageable pageable);
    Page<Book> findByPublisher(Publisher publisher, Pageable pageable);
    Page<Book> findByNameOrAuthorFullnameOrPublisherNameContainsAllIgnoreCase(String name, String authorFullname, String publisherName, Pageable pageable);

}
