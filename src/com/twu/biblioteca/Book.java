package com.twu.biblioteca;

public class Book {
    public Book(String name) {
        this.name = name;
    }

    String name;

    @Override
    public String toString() {
        return name;
    }
}

