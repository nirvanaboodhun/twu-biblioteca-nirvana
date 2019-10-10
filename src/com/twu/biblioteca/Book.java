package com.twu.biblioteca;

public class Book extends Item {
    String author;

    public Book(String name, String author, String year) {
        super(name, year);
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return getName() + " || " + getAuthor() + " || " + getYear();
    }
}

