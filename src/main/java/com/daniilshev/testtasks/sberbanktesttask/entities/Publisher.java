package com.daniilshev.testtasks.sberbanktesttask.entities;

import javax.persistence.*;

@Entity
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @Column
    String name;

    @Column
    String locate;
}
