package com.twu.biblioteca;

import java.util.ArrayList;

public class BibliotecaApp {
    public static ArrayList<Book> books = new ArrayList<Book>();

    public static void main(String[] args) {
        System.out.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");

        books.add(new Book("Harry Potter and the Chamber of Secrets"));
        books.add(new Book("1984"));

        for (Book book : books) {
            System.out.println(book.toString());
        }
    }
}
