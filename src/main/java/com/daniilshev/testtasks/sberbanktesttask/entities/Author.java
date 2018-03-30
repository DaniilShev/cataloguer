package com.daniilshev.testtasks.sberbanktesttask.entities;

import javax.persistence.*;

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @Column
    String fullname;

    @Column
    String birthYear;

    @Column
    String country;
}
