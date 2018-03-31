package com.daniilshev.testtasks.sberbanktesttask.entities;

import javax.persistence.*;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String name;

    @JoinColumn
    @ManyToOne
    private Publisher publisher;

    @JoinColumn
    @ManyToOne
    private Author author;

    @Column
    private Integer room;

    @Column
    private Long shelf;

    @Column
    private Boolean inLibrary;

    public Book() {
    }

    public Book(String name, Publisher publisher, Author author, int room, long shelf, boolean inLibrary) {
        this.name = name;
        this.publisher = publisher;
        this.author = author;
        this.room = room;
        this.shelf = shelf;
        this.inLibrary = inLibrary;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public Author getAuthor() {
        return author;
    }

    public Integer getRoom() {
        return room;
    }

    public Long getShelf() {
        return shelf;
    }

    public Boolean getInLibrary() {
        return inLibrary;
    }
}
