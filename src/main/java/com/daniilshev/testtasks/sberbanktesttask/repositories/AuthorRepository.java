package com.daniilshev.testtasks.sberbanktesttask.repositories;

import com.daniilshev.testtasks.sberbanktesttask.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
