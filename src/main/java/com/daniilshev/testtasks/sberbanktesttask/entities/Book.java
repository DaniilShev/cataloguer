package com.daniilshev.testtasks.sberbanktesttask.entities;

import javax.persistence.*;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @Column
    String name;

    @Column
    @ManyToOne
    Publisher publisher;

    @Column
    @ManyToOne
    Author author;

    @Column
    Integer room;

    @Column
    Long shelf;
}
