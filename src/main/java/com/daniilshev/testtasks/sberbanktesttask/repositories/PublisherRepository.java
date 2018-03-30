package com.daniilshev.testtasks.sberbanktesttask.repositories;

import com.daniilshev.testtasks.sberbanktesttask.entities.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {
}
