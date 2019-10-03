package com.twu.biblioteca;

public class Book {
    String name;
    String author;
    String yearPublished;


    public Book(String name, String author, String yearPublished) {
        this.name = name;
        this.author = author;
        this.yearPublished = yearPublished;
    }

    @Override
    public String toString() {
        return name + " || " + author + " || " + yearPublished;
    }
}

