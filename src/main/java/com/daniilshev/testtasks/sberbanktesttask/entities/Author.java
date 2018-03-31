package com.daniilshev.testtasks.sberbanktesttask.entities;

import javax.persistence.*;

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String fullname;

    @Column
    private String birthYear;

    @Column
    private String country;

    public Author() {
    }

    public Author(String fullname, String birthYear, String country) {
        this.fullname = fullname;
        this.birthYear = birthYear;
        this.country = country;
    }

    public long getId() {
        return id;
    }

    public String getFullname() {
        return fullname;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public String getCountry() {
        return country;
    }
}
